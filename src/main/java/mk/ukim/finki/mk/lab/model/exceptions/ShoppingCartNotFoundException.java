package mk.ukim.finki.mk.lab.model.exceptions;

public class ShoppingCartNotFoundException extends RuntimeException {
    public ShoppingCartNotFoundException(Long id) {
        super(String.format("The shopping cart with id %d was not found", id));
    }
}
