package es.cheste.frontend.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.http.HttpResponse;

public class ErrorManagement {

    private static final Logger LOGGER = LogManager.getLogger(ErrorManagement.class);

    public static void errorManager(HttpResponse<String> response) throws IOException {
        if (response.statusCode() >= 400) {

            LOGGER.error("Error: {}", response.body());
            throw new IOException(response.body());
        }
    }
}
