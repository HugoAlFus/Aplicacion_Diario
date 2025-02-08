package es.cheste.frontend.controller;

import es.cheste.frontend.dto.DiaryEntryDTO;
import es.cheste.frontend.util.WindowManagement;
import javafx.scene.control.ListView;

import java.util.List;

public class ListEntriesController {
    @javafx.fxml.FXML
    private ListView<String> lvEntries;

    private List<DiaryEntryDTO> listEntry;

    public void initializedContent(List<DiaryEntryDTO> listEntry) {
        this.listEntry = listEntry;
        loadEntries();
    }



    private void loadEntries() {

        for (DiaryEntryDTO entryDTO : listEntry) {
            lvEntries.getItems().add(entryDTO.getTitle() + " - " + entryDTO.getCreatedAt());
        }

        lvEntries.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                int index = lvEntries.getSelectionModel().getSelectedIndex();
                if (index >= 0) {
                    showEntryDetails(listEntry.get(index));
                }
            }
        });
    }

    private void showEntryDetails(DiaryEntryDTO entryDTO) {

        WindowManagement.openNewWindow("/es/cheste/frontend/app/EntryDetails.fxml", "Entry Details", null, entryDTO);

    }

}
