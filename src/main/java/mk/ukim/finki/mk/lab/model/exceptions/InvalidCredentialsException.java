package mk.ukim.finki.mk.lab.model.exceptions;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("The provided credentials are invalid");
    }
}
