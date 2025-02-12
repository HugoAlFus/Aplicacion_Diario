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
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

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

    private void initializeListView() {
        lvFiles.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                openFile(lvFiles.getSelectionModel().getSelectedItem());
            }
        });
    }

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

    private void printEntry() {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);


            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 15);
                contentStream.newLineAtOffset(100, 750);
                contentStream.showText(entryDTO.getCreatedAt().toString());
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 20);
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText(entryDTO.getTitle());
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.newLineAtOffset(100, 650);
                contentStream.showText(entryDTO.getContent());
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.newLineAtOffset(100, 600);
                contentStream.showText("Files:");
                contentStream.endText();

                int yOffset = 580;
                for (String filePath : entryDTO.getFilePaths()) {
                    contentStream.beginText();
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                    contentStream.newLineAtOffset(100, yOffset);
                    contentStream.showText(new File(filePath).getName());
                    contentStream.endText();
                    yOffset -= 20;
                }
            }
            document.save(file.getAbsolutePath() + "/" + entryDTO.getCreatedAt() + ".pdf");
            Desktop.getDesktop().open(new File(file.getAbsolutePath() + "/" + entryDTO.getCreatedAt() + ".pdf"));
        } catch (IOException e) {
            LOGGER.error("Error creating pdf {}", e.getMessage());
            DialogUtil.showDialogError("Error", "Error printing the entry", "Entry error");
        }
    }

    private void getUsername() {

        try {
            usename = userService.getUsernameById(entryDTO.getUserId());
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Error getting username {}", e.getMessage());
            DialogUtil.showDialogError("Error", "Error getting username", "Username error");
        }
    }

    private void initializeFile() {
        file = new File(BASE_PATH, usename + "/" + entryDTO.getCreatedAt());
        LOGGER.info("Path created: " + file.mkdirs());
    }
}
