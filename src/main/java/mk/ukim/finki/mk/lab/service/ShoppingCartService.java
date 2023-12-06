package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Movie;
import mk.ukim.finki.mk.lab.model.ShoppingCart;
import mk.ukim.finki.mk.lab.model.TicketOrder;

import java.time.LocalDateTime;
import java.util.List;

public interface ShoppingCartService {

    List<TicketOrder> listAllTicketsInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addMovieToCart(String username, Long orderId);
}
