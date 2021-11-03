package cinema.requests;

public final class RequestException extends RuntimeException {
    public RequestException(String error) {
        super(error);
    }
}