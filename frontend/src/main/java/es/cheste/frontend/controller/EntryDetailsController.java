package es.cheste.frontend.controller;

import es.cheste.frontend.dto.DiaryEntryDTO;
import es.cheste.frontend.service.DiaryEntryService;
import es.cheste.frontend.service.UserService;
import es.cheste.frontend.util.DialogUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Controlador para gestionar los detalles de una entrada de diario.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class EntryDetailsController {

    private static final Logger LOGGER = LogManager.getLogger(EntryDetailsController.class);
    private static final String BASE_PATH = "D:/MyDiary/pdf/";
    private final DiaryEntryService diaryEntryService = new DiaryEntryService();
    private final UserService userService = new UserService();
    private DiaryEntryDTO entryDTO;
    private String usename;
    private File file;

    @javafx.fxml.FXML
    private TextField tfTitle;
    @javafx.fxml.FXML
    private ListView<String> lvFiles;
    @javafx.fxml.FXML
    private TextArea taContent;
    @javafx.fxml.FXML
    private Label lbEntryDay;
    @javafx.fxml.FXML
    private Button btnExit;
    @javafx.fxml.FXML
    private Button btnPrint;
    @javafx.fxml.FXML
    private Button btnDeleteEntry;

    /**
     * Inicializa el contenido del controlador con la entrada de diario proporcionada.
     *
     * @param entry la entrada de diario a mostrar.
     */
    public void initializeContent(DiaryEntryDTO entry) {
        this.entryDTO = entry;
        tfTitle.setText(entry.getTitle());
        lbEntryDay.setText(entry.getCreatedAt().toString());
        taContent.setText(entry.getContent());
        entry.getFilePaths().forEach(s -> lvFiles.getItems().add(new File(s).getName()));

        initializeListView();
        getUsername();
        initializeFile();
    }

    /**
     * Inicializa la vista de lista para los archivos adjuntos.
     */
    private void initializeListView() {
        lvFiles.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                openFile(lvFiles.getSelectionModel().getSelectedItem());
            }
        });
    }

    /**
     * Abre el archivo seleccionado de la lista de archivos.
     *
     * @param fileName el nombre del archivo a abrir.
     */
    private void openFile(String fileName) {
        try {
            for (String path : entryDTO.getFilePaths()) {
                if (path.endsWith(fileName)) {
                    File file = new File(path);
                    if (file.exists()) {
                        Desktop.getDesktop().open(file);
                    }
                    break;
                }
            }
        } catch (IOException e) {
            DialogUtil.showDialogError("Error", e.getMessage(), "Error opening file");
            LOGGER.error("Error opening file {}", e.getMessage());
        }
    }

    /**
     * Maneja los eventos de clic en los botones de la interfaz.
     *
     * @param actionEvent el evento de acción.
     */
    @javafx.fxml.FXML
    public void onClick(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnDeleteEntry) {
            if (entryDTO.getUserId() == null) {
                DialogUtil.showDialogError("Error", "User ID is missing", "Deletion Failed");
                return;
            }

            try {
                String response = diaryEntryService.deleteEntry(entryDTO.getId(), entryDTO.getUserId());
                LOGGER.info("Entry deleted: {}", response);
                ((Stage) btnDeleteEntry.getScene().getWindow()).close();
            } catch (IOException | InterruptedException e) {
                LOGGER.error("Error deleting entry: {}", e.getMessage());
                DialogUtil.showDialogError("Error", e.getMessage(), "Error deleting the entry");
            }
        } else if (actionEvent.getSource() == btnExit) {
            ((Stage) btnDeleteEntry.getScene().getWindow()).close();
        } else {
            printEntry();
        }
    }

    /**
     * Imprime la entrada de diario en un archivo PDF.
     */
    private void printEntry() {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                PDType1Font font = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                float[] position = new float[2];

                position[0] = 100;
                position[1] = 750;

                writePDF(contentStream, entryDTO.getCreatedAt().toString(), font, 15, position);

                position[1] = 700;

                writePDF(contentStream, entryDTO.getTitle(), font, 20, position);

                font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
                position[1] = 650;

                writePDF(contentStream, entryDTO.getContent(), font, 12, position);

                position[1] = 600;
                writePDF(contentStream, "Files", font, 12, position);

                position[1] = 580;
                for (String filePath : entryDTO.getFilePaths()) {
                    writePDF(contentStream, new File(filePath).getName(), font, 12, position);
                    position[1] -= 20;
                }
            }
            document.save(file.getAbsolutePath() + "/" + entryDTO.getCreatedAt() + ".pdf");
            Desktop.getDesktop().open(new File(file.getAbsolutePath() + "/" + entryDTO.getCreatedAt() + ".pdf"));
        } catch (IOException e) {
            LOGGER.error("Error creating pdf {}", e.getMessage());
            DialogUtil.showDialogError("Error", "Error printing the entry", "Entry error");
        }
    }

    /**
     * Escribe texto en el archivo PDF.
     *
     * @param contentStream el flujo de contenido del PDF.
     * @param text el texto a escribir.
     * @param font la fuente del texto.
     * @param size el tamaño de la fuente.
     * @param position la posición del texto en la página.
     * @throws IOException si ocurre un error al escribir en el PDF.
     */
    private void writePDF(PDPageContentStream contentStream, String text, PDFont font, float size, float[] position) throws IOException {
        contentStream.beginText();
        contentStream.setFont(font, size);
        contentStream.newLineAtOffset(position[0], position[1]);
        contentStream.showText(text);
        contentStream.endText();
    }

    /**
     * Obtiene el nombre de usuario a partir del ID de usuario.
     */
    private void getUsername() {
        try {
            usename = userService.getUsernameById(entryDTO.getUserId());
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Error getting username {}", e.getMessage());
        }
    }

    /**
     * Inicializa el archivo para guardar el PDF.
     */
    private void initializeFile() {
        file = new File(BASE_PATH, usename + "/" + entryDTO.getCreatedAt());
        LOGGER.info("Path created: " + file.mkdirs());
    }
}