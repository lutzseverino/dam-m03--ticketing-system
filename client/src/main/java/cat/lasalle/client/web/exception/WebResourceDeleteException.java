package cat.lasalle.client.web.exception;

public class WebResourceDeleteException extends RuntimeException {
    public WebResourceDeleteException(String message) {
        super(message);
    }

    public WebResourceDeleteException(Exception e) {
        super(e);
    }
}
