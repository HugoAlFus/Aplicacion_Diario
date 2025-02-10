package es.cheste.frontend.controller;

import es.cheste.frontend.util.WindowManagement;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {
    @javafx.fxml.FXML
    private Button btnLogin;
    @javafx.fxml.FXML
    private Button btnCreateAccount;
    @javafx.fxml.FXML
    private Button btnExit;

    @javafx.fxml.FXML
    public void onClick(ActionEvent actionEvent) {

        if (actionEvent.getSource() == btnLogin) {

            WindowManagement.openNewWindow("/es/cheste/frontend/auth/login.fxml", "Login", (Stage) btnLogin.getScene().getWindow(), null);

        } else if(actionEvent.getSource() == btnCreateAccount) {
            WindowManagement.openNewWindow("/es/cheste/frontend/auth/register.fxml", "Register",(Stage) btnLogin.getScene().getWindow(), null);
        } else
            System.exit(0);
    }
}
