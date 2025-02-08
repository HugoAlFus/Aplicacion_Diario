package es.cheste.frontend.util;

import es.cheste.frontend.controller.DiaryAppController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class WindowManagement {

    private static final Logger LOGGER = LogManager.getLogger(WindowManagement.class);

    public static void openNewWindow(String path, String title, Stage currentStage, Object username){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(WindowManagement.class.getResource(path));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle(title);
            Scene scene = new Scene(root);

            String css = WindowManagement.class.getResource("/es/cheste/frontend/css/Style.css").toExternalForm();
            scene.getStylesheets().add(css);

            Object controller = fxmlLoader.getController();

            if(controller instanceof DiaryAppController){
                ((DiaryAppController) controller).setUsername((String) username);
            }

            stage.setScene(scene);
            stage.show();
            currentStage.close();
        }catch (IOException e){
            LOGGER.error("An error occurred when trying to open the window.\nPath: {}\n Error: {}", path, e.getMessage());
            System.err.println("An error ocurred");
            System.exit(1);

        }
    }
}
