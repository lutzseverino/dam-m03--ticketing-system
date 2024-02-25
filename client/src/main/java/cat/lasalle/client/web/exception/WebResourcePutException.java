package cat.lasalle.client.web.exception;

public class WebResourcePutException extends RuntimeException {
    public WebResourcePutException(String message) {
        super(message);
    }

    public WebResourcePutException(Exception e) {
        super(e);
    }
}
