package cinema.exception;

public class UnauthorizedException extends RequestException {
    public UnauthorizedException(String error) {
        super(error);
    }
}
