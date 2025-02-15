package es.cheste.frontend.controller;

import com.google.gson.Gson;
import es.cheste.frontend.dto.UserLoginDTO;
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

/**
 * Controlador para gestionar el inicio de sesión de los usuarios.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class LoginController {

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
    private final UserService userService = new UserService();

    @javafx.fxml.FXML
    private Button btnForgotPassword;
    @javafx.fxml.FXML
    private TextField tfUsername;
    @javafx.fxml.FXML
    private PasswordField pfPassword;
    @javafx.fxml.FXML
    private Button btnLog;
    @javafx.fxml.FXML
    private Label lbError;
    @javafx.fxml.FXML
    private Button btnExit;

    /**
     * Maneja los eventos de clic en los botones de la interfaz.
     *
     * @param actionEvent el evento de acción.
     */
    @javafx.fxml.FXML
    public void onClick(ActionEvent actionEvent) {

        if(actionEvent.getSource() == btnLog){

            if(!tfUsername.getText().isEmpty() || !pfPassword.getText().isEmpty()){
                logUser();
            }
        } else if(actionEvent.getSource() == btnForgotPassword){
            WindowManagement.openNewWindow("/es/cheste/frontend/auth/ChangePassword.fxml", "Change Password", (Stage) btnLog.getScene().getWindow(), null);
        } else System.exit(0);
    }

    /**
     * Inicia sesión del usuario con las credenciales proporcionadas.
     */
    private void logUser() {
        UserLoginDTO user = new UserLoginDTO(tfUsername.getText(), pfPassword.getText());

        String json = new Gson().toJson(user);
        try {
            if (!userService.loginUser(json).contains("Incorrect")){
                WindowManagement.openNewWindow("/es/cheste/frontend/app/diaryApp.fxml", "My Diary", (Stage) btnLog.getScene().getWindow(), user);
            } else {
                DialogUtil.showDialogError("Error", "Incorrect username or password", "Error login username");
                lbError.setText("Incorrect username or password");
                pfPassword.setText(null);
            }
        } catch (InterruptedException | IOException e) {
            LOGGER.error("Error logging in user: {}", e.getMessage());
            DialogUtil.showDialogError("Error", e.getMessage(), "Login error");
        }
    }
}