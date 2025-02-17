package es.cheste.frontend.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.http.HttpResponse;

/**
 * Clase de utilidad para la gestión de errores en la aplicación.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class ErrorManagement {

    private static final Logger LOGGER = LogManager.getLogger(ErrorManagement.class);

    /**
     * Gestiona los errores de una respuesta HTTP.
     *
     * @param response la respuesta HTTP a gestionar
     * @throws IOException si el código de estado de la respuesta es mayor o igual a 400
     */
    public static void errorManager(HttpResponse<String> response) throws IOException {
        if (response.statusCode() >= 400) {
            LOGGER.error("Error: {}", response.body());
            throw new IOException(response.body());
        }
    }
}