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

    /**
     * Constructs a new WebResource with the specified type and resource path.
     *
     * @param type         the class of the type parameter
     * @param resourcePath the path of the resource
     */
    protected WebResource(Class<T> type, String resourcePath) {
        this.type = type;
        this.resourcePath = resourcePath;
        this.url = "http://localhost:8080/" + resourcePath; // TODO: get from config
    }

    /**
     * Constructs a new WebResource with the specified type, resource path, and timeout.
     *
     * @param type         the class of the type parameter
     * @param resourcePath the path of the resource
     * @param timeout      the timeout for the HTTP requests
     */
    protected WebResource(Class<T> type, String resourcePath, int timeout) {
        this(type, resourcePath);
        this.timeout = timeout;
    }

    /**
     * Creates a new resource on the server.
     *
     * @param t the resource to create
     * @return the created resource
     * @throws WebResourceGetException if the server responds with a status code other than 201
     */
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

    /**
     * Reads a resource from the server by its ID.
     *
     * @param id the ID of the resource to read
     * @return the read resource
     * @throws WebResourceGetException if the server responds with a status code other than 200
     */
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

    /**
     * Updates a resource on the server.
     *
     * @param t the resource to update
     * @return the updated resource
     * @throws WebResourceGetException if the server responds with a status code other than 200
     */
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

    /**
     * Deletes a resource from the server by its ID.
     *
     * @param id the ID of the resource to delete
     * @throws WebResourceGetException if the server responds with a status code other than 200
     */
    public void delete(String id) {
        HttpRequest request = buildDeleteRequest(url + "/" + id);
        HttpResponse<String> response = sendRequest(request);

        if (response.statusCode() != 200)
            throw new WebResourceGetException("Error deleting resource: " + response.statusCode());
    }

    /**
     * Reads all resources from the server.
     *
     * @param page the page number to read
     * @param size the number of resources per page
     * @return a list of resources
     * @throws WebResourceGetException if the server responds with a status code other than 200
     */
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

    /**
     * Sends the HTTP request and returns the response.
     *
     * @param request the HTTP request to send
     * @return the HTTP response
     * @throws WebResourceRequestException if an I/O error occurs when sending or receiving
     */
    private HttpResponse<String> sendRequest(HttpRequest request) {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new WebResourceRequestException(e);
        }
    }

    /**
     * Builds a GET request with the specified URL.
     *
     * @param url the URL for the GET request
     * @return the built GET request
     */
    private HttpRequest buildGetRequest(String url) {
        return getDefaultRequest(url)
                .GET()
                .build();
    }

    /**
     * Builds a POST request with the specified URL and body.
     *
     * @param url the URL for the POST request
     * @param t   the body of the POST request
     * @return the built POST request
     * @throws WebResourcePostException if an error occurs when converting the body to JSON
     */
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

    /**
     * Builds a PUT request with the specified URL and body.
     *
     * @param url the URL for the PUT request
     * @param t   the body of the PUT request
     * @return the built PUT request
     * @throws WebResourcePutException if an error occurs when converting the body to JSON
     */
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

    /**
     * Builds a DELETE request with the specified URL.
     *
     * @param url the URL for the DELETE request
     * @return the built DELETE request
     */
    private HttpRequest buildDeleteRequest(String url) {
        return getDefaultRequest(url)
                .DELETE()
                .build();
    }

    /**
     * Returns a default HttpRequest.Builder with the specified URL and timeout.
     *
     * @param url the URL for the request
     * @return the default HttpRequest.Builder
     */
    private HttpRequest.Builder getDefaultRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(timeout));
    }
}
