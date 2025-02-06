package es.cheste.frontend.controller;

import com.google.gson.Gson;
import es.cheste.frontend.dto.UserUpdatePasswordDTO;
import es.cheste.frontend.service.UserService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ChangePassword {

    private static final Logger LOGGER = LogManager.getLogger(ChangePassword.class);

    @javafx.fxml.FXML
    private PasswordField pfCurrentPassword;
    @javafx.fxml.FXML
    private PasswordField pfNewPassword;
    @javafx.fxml.FXML
    private Button btnUpdatePassword;
    @javafx.fxml.FXML
    private TextField tfEmail;
    @javafx.fxml.FXML
    private Label lbError;

    private final UserService userService = new UserService();

    @javafx.fxml.FXML
    public void onClick(ActionEvent actionEvent) {

        if (actionEvent.getSource() == btnUpdatePassword) {

            if (tfEmail.getText() != null && pfCurrentPassword.getText() != null && pfNewPassword.getText() != null) {

                UserUpdatePasswordDTO user = new UserUpdatePasswordDTO(tfEmail.getText(), pfCurrentPassword.getText(), pfNewPassword.getText());

                String json = new Gson().toJson(user);

                try {
                    LOGGER.info(userService.updateUser(json));
                } catch (InterruptedException | IOException e) {
                    lbError.setText(e.getMessage());
                    pfNewPassword.setText(null);
                    pfCurrentPassword.setText(null);

                }
            }
        }
    }
}
