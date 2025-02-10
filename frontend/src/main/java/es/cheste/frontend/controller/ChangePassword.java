package es.cheste.frontend.controller;

import com.google.gson.Gson;
import es.cheste.frontend.dto.UserUpdatePasswordDTO;
import es.cheste.frontend.service.UserService;
import es.cheste.frontend.util.DialogUtil;
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
    @javafx.fxml.FXML
    private Button btnExit;

    private final UserService userService = new UserService();

    @javafx.fxml.FXML
    public void onClick(ActionEvent actionEvent) {

        if (actionEvent.getSource() == btnUpdatePassword) {

            if (!tfEmail.getText().isEmpty()|| !pfCurrentPassword.getText().isEmpty() || !pfNewPassword.getText().isEmpty()) {

                if(pfCurrentPassword.getText().equals(pfNewPassword.getText())) {
                    UserUpdatePasswordDTO user = new UserUpdatePasswordDTO(tfEmail.getText(), pfCurrentPassword.getText(), pfNewPassword.getText());

                    String json = new Gson().toJson(user);

                    try {
                        LOGGER.info(userService.updateUser(json));
                        WindowManagement.openNewWindow("/es/cheste/frontend/auth/login.fxml", "Login", (Stage) btnUpdatePassword.getScene().getWindow(), null);
                    } catch (InterruptedException | IOException e) {
                        DialogUtil.showDialogError("Error", e.getMessage(), "Email error");
                        lbError.setText(e.getMessage());
                        pfNewPassword.setText(null);
                        pfCurrentPassword.setText(null);

                    }
                } else {
                    DialogUtil.showDialogError("Error", "The password must be the same", "Password error");
                    lbError.setText("The password must be the same");
                    pfNewPassword.setText(null);
                    pfCurrentPassword.setText(null);
                }
            }
        } else System.exit(0);
    }
}
