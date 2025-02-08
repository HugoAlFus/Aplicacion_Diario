package es.cheste.frontend.controller;

import com.google.gson.Gson;
import es.cheste.frontend.dto.UserLoginDTO;
import es.cheste.frontend.service.UserService;
import es.cheste.frontend.util.WindowManagement;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class LoginController {

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    @javafx.fxml.FXML
    private Button btnForgotPassword;
    @javafx.fxml.FXML
    private TextField tfUsername;
    @javafx.fxml.FXML
    private PasswordField pfPassword;
    @javafx.fxml.FXML
    private Button btnLog;

    private UserService userService = new UserService();
    @javafx.fxml.FXML
    private Label lbError;

    @javafx.fxml.FXML
    public void onClick(ActionEvent actionEvent) {

        if(actionEvent.getSource() == btnLog){

            if(tfUsername.getText() != null && pfPassword.getText() != null){
                logUser();
            }
        } else if(actionEvent.getSource() == btnForgotPassword){
            WindowManagement.openNewWindow("/es/cheste/frontend/auth/ChangePassword.fxml", "Change Password", (Stage) btnLog.getScene().getWindow(), null);
        }
    }

    private void logUser() {
        UserLoginDTO user = new UserLoginDTO(tfUsername.getText(), pfPassword.getText());

        String json = new Gson().toJson(user);
        try {
            LOGGER.info(userService.loginUser(json));
            WindowManagement.openNewWindow("/es/cheste/frontend/app/diaryApp.fxml", "My Diary", (Stage) btnLog.getScene().getWindow(), user);
        } catch (InterruptedException | IOException e) {

            lbError.setText(e.getMessage());
            pfPassword.setText(null);
        }
    }
}
