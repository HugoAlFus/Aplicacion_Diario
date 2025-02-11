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

public class DiaryEntryService {

    private static final String BASE_URL = "http://localhost:8080/api/entries";
    private static final Logger LOGGER = LogManager.getLogger(DiaryEntryService.class);


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
