package mk.ukim.finki.mk.lab.model.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super(String.format("User with username %s not found", username));
    }
}
