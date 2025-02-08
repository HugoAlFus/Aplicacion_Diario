package es.cheste.frontend.controller;

import com.google.gson.Gson;
import es.cheste.frontend.dto.DiaryEntryDTO;
import es.cheste.frontend.service.DiaryEntryService;
import es.cheste.frontend.service.UserService;
import es.cheste.frontend.util.WindowManagement;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class DiaryAppController implements Initializable {

    private static final DateTimeFormatter MONTH_FORMAT = DateTimeFormatter.ofPattern("MMMM", new Locale("en", "EN"));
    private static final Logger LOGGER = LogManager.getLogger(DiaryAppController.class);
    private final UserService userService = new UserService();
    private final DiaryEntryService diaryEntryService = new DiaryEntryService();

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

    private Long userId;
    private String username;
    private DiaryEntryDTO entryDTO;
    private List<String> listPaths = new ArrayList<>();

    @javafx.fxml.FXML
    public void onClick(ActionEvent actionEvent) {

        if (actionEvent.getSource() == btnSave) {
            saveData();
        }

        if (actionEvent.getSource() == btnList) {
            listEntries();
        }
    }

    private void saveData() {

        if (entryDTO == null) {
            entryDTO = new DiaryEntryDTO(LocalDate.now(), taContent.getText(), tfTitle.getText(), listPaths);

            try {
                LOGGER.info(diaryEntryService.createEntry(new Gson().toJson(entryDTO), userId));
            } catch (IOException | InterruptedException e) {
                LOGGER.error("Error creating DiaryEntry {}", e.getMessage());
            }
        } else {
            try {
                LOGGER.info(diaryEntryService.updateEntry(new Gson().toJson(entryDTO), userId, entryDTO.getId()));
            } catch (IOException | InterruptedException e) {
                LOGGER.error("Error updating DiaryEntry {}", e.getMessage());
            }
        }

    }

    private void listEntries(){

        Gson json = new Gson();

        try {
            List<DiaryEntryDTO> list = json.fromJson(diaryEntryService.getEntriesByUserId(userId), List.class);
            WindowManagement.openNewWindow("/es/cheste/frontend/app/ListEntriesController.fxml", "Entries", (Stage) btnSave.getScene().getWindow(), list);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUserId();
        setEntryDTO();
        lbEntryDay.setText(setDate());

        if (entryDTO != null) {
            setTfTitle();
            setTaContent();
        }
    }

    private void setUserId() {

        try {
            userId = Long.valueOf(userService.getUserByUsername(username));
        } catch (IOException | InterruptedException e) {
            LOGGER.error("username not found {}", e.getMessage());
            System.err.println("An error ocurred");
            System.exit(0);
        }
    }

    private void setEntryDTO() {

        Gson json = new Gson();
        try {
            entryDTO = json.fromJson(diaryEntryService.findEntryByIdAndDate(userId, LocalDate.now()), DiaryEntryDTO.class);
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Diary entry not found {}", e.getMessage());
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

    private void setTaContent() {
        taContent.setText(entryDTO.getContent());
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
