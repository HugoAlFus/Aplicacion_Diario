package es.cheste.frontend.service;

import es.cheste.frontend.util.ErrorManagement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Servicio para gestionar las operaciones relacionadas con los usuarios.
 *
 * @author Hugo Almodóvar Fuster
 * @version 1.0
 */
public class UserService {

    private static final String BASE_URL = "http://localhost:8080/api/users";
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    /**
     * Registra un nuevo usuario.
     *
     * @param jsonBody el cuerpo de la solicitud en formato JSON.
     * @return la respuesta del servidor.
     * @throws IOException si ocurre un error de entrada/salida.
     * @throws InterruptedException si la operación es interrumpida.
     */
    public String registerUser(String jsonBody) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ErrorManagement.errorManager(response);

        return response.body();
    }

    /**
     * Inicia sesión de un usuario.
     *
     * @param jsonBody el cuerpo de la solicitud en formato JSON.
     * @return la respuesta del servidor.
     * @throws IOException si ocurre un error de entrada/salida.
     * @throws InterruptedException si la operación es interrumpida.
     */
    public String loginUser(String jsonBody) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * Actualiza la información de un usuario.
     *
     * @param jsonBody el cuerpo de la solicitud en formato JSON.
     * @return la respuesta del servidor.
     * @throws IOException si ocurre un error de entrada/salida.
     * @throws InterruptedException si la operación es interrumpida.
     */
    public String updateUser(String jsonBody) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ErrorManagement.errorManager(response);

        return response.body();
    }

    /**
     * Elimina un usuario.
     *
     * @param userId el ID del usuario.
     * @return la respuesta del servidor.
     * @throws IOException si ocurre un error de entrada/salida.
     * @throws InterruptedException si la operación es interrumpida.
     */
    public String deleteUser(Long userId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + userId))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ErrorManagement.errorManager(response);

        return response.body();
    }

    /**
     * Obtiene un usuario por su nombre de usuario.
     *
     * @param username el nombre de usuario.
     * @return la respuesta del servidor.
     * @throws IOException si ocurre un error de entrada/salida.
     * @throws InterruptedException si la operación es interrumpida.
     */
    public String getUserByUsername(String username) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + username + "/id"))
                .header("Content-Type", "application/json")
                .GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ErrorManagement.errorManager(response);

        return response.body();
    }

    /**
     * Obtiene el nombre de usuario por su ID.
     *
     * @param userId el ID del usuario.
     * @return la respuesta del servidor.
     * @throws IOException si ocurre un error de entrada/salida.
     * @throws InterruptedException si la operación es interrumpida.
     */
    public String getUsernameById(Long userId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + userId))
                .header("Content-Type", "application/json")
                .GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        LOGGER.error(response.body());

        ErrorManagement.errorManager(response);

        return response.body();
    }
}