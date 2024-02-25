package cat.lasalle.client.web.exception;

public class WebResourcePostException extends RuntimeException {
    public WebResourcePostException(String message) {
        super(message);
    }

    public WebResourcePostException(Exception e) {
        super(e);
    }
}
