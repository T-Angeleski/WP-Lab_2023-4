package mk.ukim.finki.mk.lab.model.exceptions;

public class PasswordDoesNotMatchException extends RuntimeException {
    public PasswordDoesNotMatchException(String pass, String repeat) {
        super(String.format("The passwords %s - %s do not match!", pass, repeat));
    }
}
