package es.cheste.frontend.controller;

import es.cheste.frontend.dto.DiaryEntryDTO;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
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

    public void initializeContent(DiaryEntryDTO entry) {

        tfTitle.setText(entry.getTitle());
        lbEntryDay.setText(entry.getCreatedAt().toString());
        taContent.setText(entry.getContent());
        lvFiles.getItems().addAll(entry.getFilePaths());

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
            LOGGER.error("Error opening file {}", e.getMessage());
        }
    }
}
