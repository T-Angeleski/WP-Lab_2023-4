package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Movie;
import mk.ukim.finki.mk.lab.model.TicketOrder;
import mk.ukim.finki.mk.lab.model.exceptions.MovieNotFoundException;
import mk.ukim.finki.mk.lab.repository.MovieRepository;
import mk.ukim.finki.mk.lab.repository.TicketOrderRepository;
import mk.ukim.finki.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {

    private final TicketOrderRepository ticketOrderRepository;
    private final MovieRepository movieRepository;

    public TicketOrderServiceImpl(TicketOrderRepository ticketOrderRepository, MovieRepository movieRepository) {
        this.ticketOrderRepository = ticketOrderRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, String address, long numberOfTickets) {
        Optional<TicketOrder> order = ticketOrderRepository.findByName(movieTitle);
        if (order.isEmpty()) {
            TicketOrder newOrder = new TicketOrder(movieTitle, clientName, address, numberOfTickets);
            ticketOrderRepository.save(newOrder);
            return newOrder;
        } else {
            TicketOrder updatedOrder = order.get();
            ticketOrderRepository.save(updatedOrder);
            return updatedOrder;
        }
    }

    @Override
    public List<TicketOrder> findAll() {
        return ticketOrderRepository.findAll();
    }

    @Override
    public Map.Entry<String, Long> findMostPopular() {
        return ticketOrderRepository.findByMostTickets();
    }

    @Override
    public List<Movie> findClientOrders(String clientName) {
        List<String> titles = ticketOrderRepository.findMoviesByClient(clientName);
        return titles.stream()
                .map(t -> movieRepository.findByTitle(t)
                        .orElseThrow(() -> new MovieNotFoundException(t)))
                .collect(Collectors.toList());
    }
}
