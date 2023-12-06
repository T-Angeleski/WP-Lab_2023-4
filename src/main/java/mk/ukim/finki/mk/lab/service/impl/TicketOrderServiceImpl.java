package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Movie;
import mk.ukim.finki.mk.lab.model.TicketOrder;
import mk.ukim.finki.mk.lab.model.User;
import mk.ukim.finki.mk.lab.repository.jpa.JpaMovieRepository;
import mk.ukim.finki.mk.lab.repository.jpa.JpaTicketOrderRepository;
import mk.ukim.finki.mk.lab.repository.jpa.JpaUserRepository;
import mk.ukim.finki.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {

    private final JpaTicketOrderRepository ticketOrderRepository;
    private final JpaMovieRepository movieRepository;
    private final JpaUserRepository userRepository;

    public TicketOrderServiceImpl(JpaTicketOrderRepository ticketOrderRepository,
                                  JpaMovieRepository movieRepository,
                                  JpaUserRepository userRepository) {
        this.ticketOrderRepository = ticketOrderRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }


//    @Override
//    public TicketOrder placeOrder(String movieTitle, String clientName, String address, long numberOfTickets) {
//        Optional<TicketOrder> order = ticketOrderRepository.findByName(movieTitle);
//        if (order.isEmpty()) {
//            TicketOrder newOrder = new TicketOrder(movieTitle, numberOfTickets);
//            ticketOrderRepository.save(newOrder);
//            return newOrder;
//        } else {
//            TicketOrder updatedOrder = order.get();
//            ticketOrderRepository.save(updatedOrder);
//            return updatedOrder;
//        }
//    }

    @Override
    public TicketOrder placeOrder(User user, Movie movie, Long numTickets, LocalDateTime timeCreated) {
        TicketOrder order = new TicketOrder(numTickets, movie, user, timeCreated);
        return ticketOrderRepository.save(order);
    }

    @Override
    public List<TicketOrder> findAll() {
        return ticketOrderRepository.findAll();
    }

    @Override
    public List<TicketOrder> findAllByUserId(Long userId) {
        return ticketOrderRepository.findAllByUserId(userId);
    }

    @Override
    public List<TicketOrder> findOrdersInTimeInterval(LocalDateTime from, LocalDateTime to) {
        return ticketOrderRepository.findAllByDateCreatedBetween(from, to);
    }

//    @Override
//    public Map.Entry<String, Long> findMostPopular() {
//        return ticketOrderRepository.findByMostTickets();
//    }
//
//    @Override
//    public List<Movie> findClientOrders(String clientName) {
//        List<String> titles = ticketOrderRepository.findMoviesByClient(clientName);
//        return titles.stream()
//                .map(t -> movieRepository.findByTitle(t)
//                        .orElseThrow(() -> new MovieNotFoundException(t)))
//                .collect(Collectors.toList());
//    }
}
