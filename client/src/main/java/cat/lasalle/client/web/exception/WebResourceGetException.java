package cat.lasalle.client.web.exception;

public class WebResourceGetException extends RuntimeException {
    public WebResourceGetException(String message) {
        super(message);
    }

    public WebResourceGetException(Exception e) {
        super(e);
    }
}
