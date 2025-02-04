package es.cheste.frontend.controller;

import com.google.gson.Gson;
import es.cheste.frontend.dto.UserRegisterDTO;
import es.cheste.frontend.service.UserService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {
    @javafx.fxml.FXML
    private TextField tfUsername;
    @javafx.fxml.FXML
    private PasswordField pfPassword;
    @javafx.fxml.FXML
    private TextField tfEmail;
    @javafx.fxml.FXML
    private Button btnCreateAccount;

    private final UserService userService = new UserService();
    @javafx.fxml.FXML
    private Label lbError;

    @javafx.fxml.FXML
    public void onClick(ActionEvent actionEvent) {

        if (actionEvent.getSource().equals(btnCreateAccount)) {

            if (tfUsername.getText() != null && pfPassword.getText() != null && tfEmail.getText() != null) {

                UserRegisterDTO user = new UserRegisterDTO(tfUsername.getText(), tfEmail.getText(), pfPassword.getText());

                String json = new Gson().toJson(user);

                try {

                    userService.registerUser(json);

                } catch (InterruptedException | IOException e) {
                    lbError.setText(e.getMessage());
                    tfEmail.setText(null);
                    pfPassword.setText(null);
                }
            }
        }
    }
}
