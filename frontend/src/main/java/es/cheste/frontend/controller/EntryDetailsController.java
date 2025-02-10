package es.cheste.frontend.controller;

import es.cheste.frontend.dto.DiaryEntryDTO;
import es.cheste.frontend.util.DialogUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EntryDetailsController {

    private static final Logger LOGGER = LogManager.getLogger(EntryDetailsController.class);
    @javafx.fxml.FXML
    private TextField tfTitle;
    @javafx.fxml.FXML
    private ListView<String> lvFiles;
    @javafx.fxml.FXML
    private TextArea taContent;
    @javafx.fxml.FXML
    private Label lbEntryDay;

    private DiaryEntryDTO entryDTO;
    @javafx.fxml.FXML
    private Button btnExit;

    public void initializeContent(DiaryEntryDTO entry) {

        tfTitle.setText(entry.getTitle());
        lbEntryDay.setText(entry.getCreatedAt().toString());
        taContent.setText(entry.getContent());
        entry.getFilePaths().forEach(s -> lvFiles.getItems().add(new File(s).getName()));

        this.entryDTO = entry;
        initializeListView();
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

        if (actionEvent.getSource() == btnExit) {
            ((Stage) btnExit.getScene().getWindow()).close();
        }

    }
}
