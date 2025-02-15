package es.cheste.frontend.service;

import es.cheste.frontend.util.ErrorManagement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

/**
 * Servicio para gestionar las entradas de diario.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class DiaryEntryService {

    private static final String BASE_URL = "http://localhost:8080/api/entries";
    private static final Logger LOGGER = LogManager.getLogger(DiaryEntryService.class);

    /**
     * Crea una nueva entrada de diario.
     *
     * @param jsonBody el cuerpo de la solicitud en formato JSON.
     * @param userId el ID del usuario.
     * @return la respuesta del servidor.
     * @throws IOException si ocurre un error de entrada/salida.
     * @throws InterruptedException si la operación es interrumpida.
     */
    public String createEntry(String jsonBody, Long userId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "?userId=" + userId))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ErrorManagement.errorManager(response);

        return response.body();
    }

    /**
     * Actualiza una entrada de diario existente.
     *
     * @param jsonBody el cuerpo de la solicitud en formato JSON.
     * @param userId el ID del usuario.
     * @param entryId el ID de la entrada de diario.
     * @return la respuesta del servidor.
     * @throws IOException si ocurre un error de entrada/salida.
     * @throws InterruptedException si la operación es interrumpida.
     */
    public String updateEntry(String jsonBody, Long userId, Long entryId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + entryId + "?userId=" + userId))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ErrorManagement.errorManager(response);

        return response.body();
    }

    /**
     * Elimina una entrada de diario.
     *
     * @param entryId el ID de la entrada de diario.
     * @param userId el ID del usuario.
     * @return la respuesta del servidor.
     * @throws IOException si ocurre un error de entrada/salida.
     * @throws InterruptedException si la operación es interrumpida.
     */
    public String deleteEntry(Long entryId, Long userId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + entryId + "?userId=" + userId))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
        LOGGER.info("UserId deleteEntry: " + userId);
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ErrorManagement.errorManager(response);

        return response.body();
    }

    /**
     * Obtiene todas las entradas de diario de un usuario.
     *
     * @param userId el ID del usuario.
     * @return la respuesta del servidor.
     * @throws IOException si ocurre un error de entrada/salida.
     * @throws InterruptedException si la operación es interrumpida.
     */
    public String getEntriesByUserId(Long userId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "?userId=" + userId))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ErrorManagement.errorManager(response);

        return response.body();
    }

    /**
     * Encuentra una entrada de diario por ID de usuario y fecha.
     *
     * @param userId el ID del usuario.
     * @param date la fecha de la entrada de diario.
     * @return la respuesta del servidor.
     * @throws IOException si ocurre un error de entrada/salida.
     * @throws InterruptedException si la operación es interrumpida.
     */
    public String findEntryByIdAndDate(Long userId, LocalDate date) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/by-date?userId=" + userId + "&date=" + date))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ErrorManagement.errorManager(response);

        return response.body();
    }
}