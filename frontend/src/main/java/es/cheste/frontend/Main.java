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
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cheste/frontend/auth/start.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Start");
        Scene scene = new Scene(root);

        String css = WindowManagement.class.getResource("/es/cheste/frontend/css/Style.css").toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}