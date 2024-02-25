package cat.lasalle.client.web.exception;

public class WebResourceRequestException extends RuntimeException {
    public WebResourceRequestException(String message) {
        super(message);
    }

    public WebResourceRequestException(Exception e) {
        super(e);
    }
}
