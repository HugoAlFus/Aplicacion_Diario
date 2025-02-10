package es.cheste.frontend;

import es.cheste.frontend.util.WindowManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        WindowManagement.openNewWindow("/es/cheste/frontend/auth/start.fxml", "Start", primaryStage, null);
    }

    public static void main(String[] args) {
        launch(args);
    }
}