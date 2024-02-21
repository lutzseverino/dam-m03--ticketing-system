package cat.lasalle.client.web;

import cat.lasalle.client.web.exception.WebResourceAccessException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public abstract class WebResource<T> {
    private final String url;
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> type;

    protected WebResource(Class<T> type, String name) {
        this.type = type;
        this.url = "http://localhost:8080/" + name + "/"; // TODO: get from config
    }

    public T create(T t) {
        return null; // TODO
    }

    public T readById(String id) throws WebResourceAccessException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url + id))
                    .timeout(java.time.Duration.ofSeconds(20))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), type);
            } else {
                throw new WebResourceAccessException("Error: " + response.statusCode());
            }
        } catch (URISyntaxException | IOException e) {
            throw new WebResourceAccessException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new WebResourceAccessException(e);
        }
    }

    public T update(T t) {
        return null; // TODO
    }

    public void delete(String id) {
        // TODO
    }

    public List<T> readAll() {
        return List.of(); // TODO
    }
}
