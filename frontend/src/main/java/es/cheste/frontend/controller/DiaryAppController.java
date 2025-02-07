package es.cheste.frontend.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class DiaryAppController {
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
    public void onClick(ActionEvent actionEvent) {
    }
}
