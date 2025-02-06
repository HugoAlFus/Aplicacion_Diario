package es.cheste.frontend.controller;

import com.google.gson.Gson;
import es.cheste.frontend.dto.UserRegisterDTO;
import es.cheste.frontend.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class RegisterController {

    private static final Logger LOGGER = LogManager.getLogger(RegisterController.class);

    @javafx.fxml.FXML
    private TextField tfUsername;
    @javafx.fxml.FXML
    private PasswordField pfPassword;
    @javafx.fxml.FXML
    private TextField tfEmail;
    @javafx.fxml.FXML
    private Button btnCreateAccount;
    @javafx.fxml.FXML
    private Label lbError;

    private final UserService userService = new UserService();

    @javafx.fxml.FXML
    public void onClick(ActionEvent actionEvent) {

        if (actionEvent.getSource() == btnCreateAccount) {

            if (tfUsername.getText() != null && pfPassword.getText() != null && tfEmail.getText() != null) {

                UserRegisterDTO user = new UserRegisterDTO(tfUsername.getText(), tfEmail.getText(), pfPassword.getText());

                String json = new Gson().toJson(user);

                try {

                    LOGGER.info(userService.registerUser(json));

                } catch (InterruptedException | IOException e) {
                    lbError.setText(e.getMessage());
                    tfEmail.setText(null);
                    pfPassword.setText(null);
                }
            }
        }
    }
}
