package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Movie;
import mk.ukim.finki.mk.lab.model.ShoppingCart;
import mk.ukim.finki.mk.lab.model.TicketOrder;
import mk.ukim.finki.mk.lab.model.User;
import mk.ukim.finki.mk.lab.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.mk.lab.model.exceptions.MovieNotFoundException;
import mk.ukim.finki.mk.lab.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.mk.lab.model.exceptions.UserNotFoundException;
import mk.ukim.finki.mk.lab.repository.jpa.JpaMovieRepository;
import mk.ukim.finki.mk.lab.repository.jpa.JpaShoppingCartRepository;
import mk.ukim.finki.mk.lab.repository.jpa.JpaTicketOrderRepository;
import mk.ukim.finki.mk.lab.repository.jpa.JpaUserRepository;
import mk.ukim.finki.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final JpaShoppingCartRepository shoppingCartRepository;
    private final JpaUserRepository userRepository;
    private final JpaMovieRepository movieRepository;
    private final JpaTicketOrderRepository ticketOrderRepository;

    public ShoppingCartServiceImpl(JpaShoppingCartRepository shoppingCartRepository,
                                   JpaUserRepository userRepository,
                                   JpaMovieRepository movieRepository, JpaTicketOrderRepository ticketOrderRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.ticketOrderRepository = ticketOrderRepository;
    }

    @Override
    public List<TicketOrder> listAllTicketsInShoppingCart(Long cartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ShoppingCartNotFoundException(cartId));
        return shoppingCart.getTicketOrders();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return shoppingCartRepository.findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addMovieToCart(String username, Long orderId) {
        ShoppingCart cart = getActiveShoppingCart(username);
        TicketOrder order = ticketOrderRepository.findById(orderId).get();
        cart.getTicketOrders().removeIf(o -> Objects.equals(o.getId(), orderId));
        cart.getTicketOrders().add(order);
        return shoppingCartRepository.save(cart);
    }

}
