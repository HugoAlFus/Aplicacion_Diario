package es.cheste.frontend.controller;

import es.cheste.frontend.dto.DiaryEntryDTO;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EntryDetailsController {
    @javafx.fxml.FXML
    private TextField tfTitle;
    @javafx.fxml.FXML
    private ListView<String> lvFiles;
    @javafx.fxml.FXML
    private TextArea taContent;
    @javafx.fxml.FXML
    private Label lbEntryDay;

    public void initializeContent(DiaryEntryDTO entry) {

        tfTitle.setText(entry.getTitle());
        lbEntryDay.setText(entry.getCreatedAt().toString());
        taContent.setText(entry.getContent());
        lvFiles.getItems().addAll(entry.getFilePaths());
    }
}
