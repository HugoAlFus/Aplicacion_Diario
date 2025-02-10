package es.cheste.frontend.util;

import javafx.scene.control.Alert;

public class DialogUtil {

    public static void showDialogError(String title, String content, String header){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
