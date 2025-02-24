package es.cheste.frontend.controller;

import com.google.gson.Gson;
import es.cheste.frontend.dto.UserRegisterDTO;
import es.cheste.frontend.service.UserService;
import es.cheste.frontend.util.DialogUtil;
import es.cheste.frontend.util.WindowManagement;
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

/**
 * Controlador para gestionar el registro de nuevos usuarios.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class RegisterController {

    private static final Logger LOGGER = LogManager.getLogger(RegisterController.class);
    private final UserService userService = new UserService();

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
    @FXML
    private Button btnExit;
    @FXML
    private PasswordField pfPasswordRepeat;

    /**
     * Maneja los eventos de clic en los botones de la interfaz.
     *
     * @param actionEvent el evento de acción.
     */
    @javafx.fxml.FXML
    public void onClick(ActionEvent actionEvent) {

        if (actionEvent.getSource() == btnCreateAccount) {

            if(!pfPasswordRepeat.getText().equals(pfPassword.getText())) {
                DialogUtil.showDialogError("Error", "Password do not match", "Error login user");
                lbError.setText("The password must be the same");
                pfPassword.setText(null);
                pfPasswordRepeat.setText(null);
                return;
            }

            if (!tfUsername.getText().isEmpty() || !pfPassword.getText().isEmpty() || !tfEmail.getText().isEmpty()) {

                UserRegisterDTO user = new UserRegisterDTO(tfUsername.getText(), tfEmail.getText(), pfPassword.getText());

                String json = new Gson().toJson(user);

                try {

                    LOGGER.info(userService.registerUser(json));
                    WindowManagement.openNewWindow("/es/cheste/frontend/app/diaryApp.fxml", "My Diary", (Stage) tfEmail.getScene().getWindow(), user);

                } catch (InterruptedException | IOException e) {
                    DialogUtil.showDialogError("Error", e.getMessage(), "Register error");
                    lbError.setText(e.getMessage());
                    tfEmail.setText(null);
                    pfPassword.setText(null);
                }
            }
        } else System.exit(0);
    }
}