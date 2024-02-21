package cat.lasalle.client.web.exception;

public class WebResourceAccessException extends RuntimeException {
    public WebResourceAccessException(String message) {
        super(message);
    }

    public WebResourceAccessException(Exception e) {
        super(e);
    }
}
