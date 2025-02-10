package es.cheste.frontend.controller;

import com.google.gson.reflect.TypeToken;
import es.cheste.frontend.dto.DiaryEntryDTO;
import es.cheste.frontend.service.DiaryEntryService;
import es.cheste.frontend.service.UserService;
import es.cheste.frontend.util.DialogUtil;
import es.cheste.frontend.util.GsonUtil;
import es.cheste.frontend.util.WindowManagement;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DiaryAppController {

    private static final DateTimeFormatter MONTH_FORMAT = DateTimeFormatter.ofPattern("MMMM", new Locale("en", "EN"));
    private static final Logger LOGGER = LogManager.getLogger(DiaryAppController.class);
    private final UserService userService = new UserService();
    private final DiaryEntryService diaryEntryService = new DiaryEntryService();
    private final FileChooser fileChooser = new FileChooser();

    @javafx.fxml.FXML
    private TextField tfTitle;
    @javafx.fxml.FXML
    private TextArea taContent;
    @javafx.fxml.FXML
    private Label lbEntryDay;
    @javafx.fxml.FXML
    private Button btnSave;
    @javafx.fxml.FXML
    private Button btnList;
    @javafx.fxml.FXML
    private Button btnAddFile;
    @javafx.fxml.FXML
    private ListView<String> lvFiles;
    @javafx.fxml.FXML
    private Button btnExit;

    private Long userId;
    private String username;
    private DiaryEntryDTO entryDTO;
    private List<String> listPaths = new ArrayList<>();

    @javafx.fxml.FXML
    public void onClick(ActionEvent actionEvent) {

        if (actionEvent.getSource() == btnSave) {
            saveData();
        } else if (actionEvent.getSource() == btnList) {
            listEntries();
        } else if (actionEvent.getSource() == btnAddFile) {
            chooseFile();
        } else System.exit(0);
    }

    private void saveData() {

        if (entryDTO == null) {
            entryDTO = new DiaryEntryDTO(LocalDate.now(), taContent.getText(), tfTitle.getText(), listPaths);

            try {
                LOGGER.info(diaryEntryService.createEntry(GsonUtil.getGson().toJson(entryDTO), userId));
            } catch (IOException | InterruptedException e) {
                DialogUtil.showDialogError("Error", e.getMessage(), "Error saving");
                LOGGER.error("Error creating DiaryEntry {}", e.getMessage());
            }
        } else {
            try {
                LOGGER.info(diaryEntryService.updateEntry(GsonUtil.getGson().toJson(entryDTO), userId, entryDTO.getId()));
            } catch (IOException | InterruptedException e) {
                DialogUtil.showDialogError("Error", e.getMessage(), "Error updating");
                LOGGER.error("Error updating DiaryEntry {}", e.getMessage());
            }
        }

    }

    private void chooseFile() {


        File file = fileChooser.showOpenDialog(btnList.getScene().getWindow());

        if (file != null) {
            listPaths.add(file.getAbsolutePath());
            lvFiles.getItems().add(file.getName());
        }
    }

    private void listEntries() {
        try {
            Type lisType = new TypeToken<List<DiaryEntryDTO>>() {
            }.getType();
            List<DiaryEntryDTO> list = GsonUtil.getGson().fromJson(diaryEntryService.getEntriesByUserId(userId), lisType);
            WindowManagement.openNewWindow("/es/cheste/frontend/app/ListEntriesController.fxml", "Entries", (Stage) btnSave.getScene().getWindow(), list);
        } catch (IOException | InterruptedException e) {
            DialogUtil.showDialogError("Error", e.getMessage(), "List entry error");
            LOGGER.error("Error in the listEntries {}", e.getMessage());
        }
    }

    public void initializeContent(String username) {

        this.username = username;
        setUserId();
        setEntryDTO();
        lbEntryDay.setText(setDate());
        initializePaths();
        initializeListView();

        if (entryDTO != null) {
            setTfTitle();
            setTaContent();
            setLvFiles();
        }
    }

    private void initializePaths() {
        fileChooser.setTitle("Select a file");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text files", "*.txt", "*.pdf"),
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
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
            for (String path : listPaths) {
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

    private void setUserId() {

        try {
            userId = Long.valueOf(userService.getUserByUsername(username));
        } catch (IOException | InterruptedException e) {
            DialogUtil.showDialogError("Error", e.getMessage(), "Error searching the user");
            LOGGER.error("username not found {}", e.getMessage());
            System.exit(0);
        }
    }

    private void setEntryDTO() {

        try {
            entryDTO = GsonUtil.getGson().fromJson(diaryEntryService.findEntryByIdAndDate(userId, LocalDate.now()), DiaryEntryDTO.class);
        } catch (IOException | InterruptedException e) {
            LOGGER.info("Diary entry not found {}", e.getMessage());
            entryDTO = null;
        }
    }

    private String setDate() {

        LocalDate date = LocalDate.now();
        StringBuilder sb = new StringBuilder();

        sb.append(date.getDayOfMonth());
        sb.append(" ").append(date.format(MONTH_FORMAT));
        sb.append(" ").append(date.getYear());

        return sb.toString();
    }

    private void setTfTitle() {
        tfTitle.setText(entryDTO.getTitle());
    }

    private void setLvFiles() {
        this.listPaths = entryDTO.getFilePaths();

        listPaths.forEach(s -> lvFiles.getItems().add(new File(s).getName()));
    }

    private void setTaContent() {
        taContent.setText(entryDTO.getContent());
    }
}
