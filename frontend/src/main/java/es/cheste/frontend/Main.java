package es.cheste.frontend;

import es.cheste.frontend.util.WindowManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Clase principal de la aplicación JavaFX.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Método de inicio de la aplicación JavaFX.
     *
     * @param primaryStage la ventana principal de la aplicación
     */
    @Override
    public void start(Stage primaryStage) {
        WindowManagement.openNewWindow("/es/cheste/frontend/auth/start.fxml", "Start", primaryStage, null);
    }

    /**
     * Método principal que lanza la aplicación.
     *
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }
}