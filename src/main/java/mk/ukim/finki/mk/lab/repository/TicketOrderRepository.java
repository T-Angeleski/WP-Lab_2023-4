package mk.ukim.finki.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.mk.lab.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TicketOrderRepository {
    public static List<TicketOrder> ticketOrders = null;
    public static Map<String, Long> ticketsByName = null;

    @PostConstruct
    public void init() {
        ticketOrders = new ArrayList<>();
        ticketsByName = new HashMap<>();
    }

    public List<TicketOrder> findAll() {
        return ticketOrders;
    }

    public TicketOrder save(TicketOrder order) {
        ticketOrders.removeIf(o -> o.getMovieTitle().equals(order.getMovieTitle()));
        ticketOrders.add(order);


        ticketsByName.computeIfPresent(order.getMovieTitle(),
                (k, v) -> v + order.getNumberOfTickets());
        ticketsByName.putIfAbsent(order.getMovieTitle(), order.getNumberOfTickets());
        return order;
    }

    public Optional<TicketOrder> findByName(String name) {
        return ticketOrders.stream()
                .filter(t -> t.getMovieTitle().contains(name))
                .findFirst();
    }

    public Map.Entry<String, Long> findByMostTickets() {
        return ticketsByName.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
    }

}
