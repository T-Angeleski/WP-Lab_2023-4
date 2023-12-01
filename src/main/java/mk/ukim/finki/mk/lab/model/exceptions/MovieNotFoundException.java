package mk.ukim.finki.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(Long id) {
        super(String.format("Movie with id %d was not found", id));
    }
    public MovieNotFoundException(String title) {
        super(String.format("Movie with title %s was not found", title));
    }
}
