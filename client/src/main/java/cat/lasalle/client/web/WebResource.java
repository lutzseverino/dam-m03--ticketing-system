package cat.lasalle.client.web;

import cat.lasalle.client.web.exception.WebResourceGetException;
import cat.lasalle.client.web.exception.WebResourcePostException;
import cat.lasalle.client.web.exception.WebResourcePutException;
import cat.lasalle.client.web.exception.WebResourceRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public abstract class WebResource<T> {
    @Getter private final String url;
    @Getter private final String resourcePath;
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> type;
    private int timeout = 20;

    protected WebResource(Class<T> type, String resourcePath) {
        this.type = type;
        this.resourcePath = resourcePath;
        this.url = "http://localhost:8080/" + resourcePath; // TODO: get from config
    }

    protected WebResource(Class<T> type, String resourcePath, int timeout) {
        this(type, resourcePath);
        this.timeout = timeout;
    }

    public T create(T t) {
        try {
            HttpRequest request = buildPostRequest(url, t);
            HttpResponse<String> response = sendRequest(request);

            if (response.statusCode() == 201) {
                return objectMapper.readValue(response.body(), type);
            } else {
                throw new WebResourceGetException("Error creating resource: " + response.statusCode());
            }
        } catch (IOException e) {
            throw new WebResourceGetException(e);
        }
    }

    public T readById(String id) throws WebResourceGetException {
        HttpRequest request = buildGetRequest(url + "/" + id);
        HttpResponse<String> response = sendRequest(request);

        try {
            if (response.statusCode() != 200)
                throw new WebResourceGetException("Error reading resource by ID: " + response.statusCode());

            return objectMapper.readValue(response.body(), type);
        } catch (IOException e) {
            throw new WebResourceGetException(e);
        }
    }

    public T update(T t) {
        HttpRequest request = buildPutRequest(url, t);
        HttpResponse<String> response = sendRequest(request);

        try {
            if (response.statusCode() != 200)
                throw new WebResourceGetException("Error updating resource: " + response.statusCode());

            return objectMapper.readValue(response.body(), type);
        } catch (IOException e) {
            throw new WebResourceGetException(e);
        }
    }

    public void delete(String id) {
        HttpRequest request = buildDeleteRequest(url + "/" + id);
        HttpResponse<String> response = sendRequest(request);

        if (response.statusCode() != 200)
            throw new WebResourceGetException("Error deleting resource: " + response.statusCode());
    }

    public List<T> readAll(int page, int size) {
        HttpRequest request = buildGetRequest(url + "?page=" + page + "&size=" + size);
        HttpResponse<String> response = sendRequest(request);

        try {
            if (response.statusCode() != 200)
                throw new WebResourceGetException("Error reading all resources: " + response.statusCode());

            return objectMapper.readValue(response.body(), objectMapper.getTypeFactory().constructCollectionType(List.class, type));
        } catch (IOException e) {
            throw new WebResourceGetException(e);
        }
    }

    private HttpResponse<String> sendRequest(HttpRequest request) {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new WebResourceRequestException(e);
        }
    }

    private HttpRequest buildGetRequest(String url) {
        return getDefaultRequest(url)
                .GET()
                .build();
    }

    private HttpRequest buildPostRequest(String url, T t) {
        try {
            return getDefaultRequest(url)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(t)))
                    .build();
        } catch (JsonProcessingException e) {
            throw new WebResourcePostException(e);
        }
    }

    private HttpRequest buildPutRequest(String url, T t) {
        try {
            return getDefaultRequest(url)
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(t)))
                    .build();
        } catch (JsonProcessingException e) {
            throw new WebResourcePutException(e);
        }
    }

    private HttpRequest buildDeleteRequest(String url) {
        return getDefaultRequest(url)
                .DELETE()
                .build();
    }

    private HttpRequest.Builder getDefaultRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(timeout));
    }
}
