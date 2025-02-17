package es.cheste.frontend.util;

import javafx.scene.control.Alert;

/**
 * Utilidad para mostrar diálogos de error en una aplicación JavaFX.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class DialogUtil {

    /**
     * Muestra un diálogo de error con el título, contenido y encabezado especificados.
     *
     * @param title   el título del diálogo
     * @param content el contenido del mensaje de error
     * @param header  el encabezado del diálogo
     */
    public static void showDialogError(String title, String content, String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}