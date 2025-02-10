package es.cheste.frontend.util;

import es.cheste.frontend.controller.DiaryAppController;
import es.cheste.frontend.controller.EntryDetailsController;
import es.cheste.frontend.controller.ListEntriesController;
import es.cheste.frontend.dto.DiaryEntryDTO;
import es.cheste.frontend.dto.IUserDTO;
import es.cheste.frontend.dto.UserLoginDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class WindowManagement {

    private static final Logger LOGGER = LogManager.getLogger(WindowManagement.class);

    private static double xOffset = 0;
    private static double yOffset = 0;

    public static void openNewWindow(String path, String title, Stage currentStage, Object object) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(WindowManagement.class.getResource(path));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle(title);
            Scene scene = new Scene(root);

            String css = WindowManagement.class.getResource("/es/cheste/frontend/css/Style.css").toExternalForm();
            scene.getStylesheets().add(css);

            Object controller = fxmlLoader.getController();

            if (controller instanceof DiaryAppController) {
                ((DiaryAppController) controller).initializeContent(((IUserDTO) object).getUsername());
            }
            if (controller instanceof ListEntriesController) {
                ((ListEntriesController) controller).initializedContent((List<DiaryEntryDTO>) object);
            }
            if(controller instanceof EntryDetailsController){
                ((EntryDetailsController) controller).initializeContent(((DiaryEntryDTO) object));
            }

            root.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            });

            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

            if (currentStage != null) {
                currentStage.close();
            }

        }catch (IOException e){
            LOGGER.error("An error occurred when trying to open the window.\nPath: {}\n Error: {}", path, e.getMessage());
            System.err.println("An error ocurred");
            System.exit(1);

        }
    }
}
