package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Movie;
import mk.ukim.finki.mk.lab.model.TicketOrder;
import mk.ukim.finki.mk.lab.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TicketOrderService {
    TicketOrder placeOrder(User user, Movie movie, Long numTickets, LocalDateTime timeCreated);

    List<TicketOrder> findAll();

    List<TicketOrder> findAllByUserId(Long userId);

    List<TicketOrder> findOrdersInTimeInterval(LocalDateTime from, LocalDateTime to);

//    Map.Entry<String, Long> findMostPopular();

//    List<Movie> findClientOrders(String clientName);
}
