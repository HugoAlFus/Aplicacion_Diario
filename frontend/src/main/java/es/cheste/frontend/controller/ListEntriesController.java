package es.cheste.frontend.controller;

import es.cheste.frontend.dto.DiaryEntryDTO;
import es.cheste.frontend.util.DialogUtil;
import es.cheste.frontend.util.WindowManagement;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class ListEntriesController {

    @javafx.fxml.FXML
    private Button btnExit;
    @javafx.fxml.FXML
    private DatePicker dpEntries;

    private List<DiaryEntryDTO> listEntry;
    @javafx.fxml.FXML
    private ListView<String> lvEntries;

    public void initializedContent(List<DiaryEntryDTO> listEntry) {
        this.listEntry = listEntry;
        loadEntries();
    }

    private void loadEntries() {
        dpEntries.setOnAction(event -> {
            LocalDate selectedDate = dpEntries.getValue();
            if (selectedDate != null) {
                showEntriesForDate(selectedDate);
            }
        });

        listEntry.forEach(entryDTO -> lvEntries.getItems().add(entryDTO.getTitle() + " - " + entryDTO.getCreatedAt()));
        lvEntries.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                int index = lvEntries.getSelectionModel().getSelectedIndex();
                if (index >= 0) {
                    showEntryDetails(listEntry.get(index));
                }
            }
        });
    }

    private void showEntriesForDate(LocalDate date) {
        boolean thereIsEntry = Boolean.FALSE;

        for (DiaryEntryDTO entryDTO : listEntry) {
            if (entryDTO.getCreatedAt().equals(date)) {
                showEntryDetails(entryDTO);
                thereIsEntry = Boolean.TRUE;
                break;
            }
        }
        if (!thereIsEntry) {
            DialogUtil.showDialogError("Error", "There is not entry the date " + date, "Date Error");
        }
    }

    private void showEntryDetails(DiaryEntryDTO entryDTO) {
        WindowManagement.openNewWindow("/es/cheste/frontend/app/EntryDetails.fxml", "Entry Details", null, entryDTO);
    }

    @javafx.fxml.FXML
    public void onClick(ActionEvent actionEvent) {

        if(actionEvent.getSource() == btnExit){
            ((Stage) btnExit.getScene().getWindow()).close();
        }
    }
}
