package mk.ukim.finki.mk.lab.repository.inMemory;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.mk.lab.model.Movie;
import mk.ukim.finki.mk.lab.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TicketOrderRepository {
    public static List<TicketOrder> ticketOrders = null;
    public static Map<String, Long> ticketsByName = null;
    public static Map<String, List<String>> moviesByClientName = null;

    @PostConstruct
    public void init() {
        ticketOrders = new ArrayList<>();
        ticketsByName = new HashMap<>();
        moviesByClientName = new HashMap<>();
    }

    public List<TicketOrder> findAll() {
        return ticketOrders;
    }

    public TicketOrder save(TicketOrder order) {
        ticketOrders.removeIf(o -> o.getMovie().getTitle().equals(order.getMovie().getTitle()));
        ticketOrders.add(order);


        ticketsByName.computeIfPresent(order.getMovie().getTitle(),
                (k, v) -> v + order.getNumberOfTickets());
        ticketsByName.putIfAbsent(order.getMovie().getTitle(), order.getNumberOfTickets());

        // Add ordered movie to client name
//        String movieTitle = order.getMovieTitle();
//        moviesByClientName.computeIfPresent(order.getClientName(),
//                (k,v) -> {
//                    v.removeIf(m -> m.equals(movieTitle));
//                    v.add(movieTitle);
//                    return v;
//                });
//        moviesByClientName.putIfAbsent(order.getClientName(),
//                new ArrayList<>());
//        moviesByClientName.get(order.getClientName()).add(movieTitle);

        return order;
    }

    public Optional<TicketOrder> findByName(String name) {
        return ticketOrders.stream()
                .filter(t -> t.getMovie().getTitle().contains(name))
                .findFirst();
    }

    public Map.Entry<String, Long> findByMostTickets() {
        return ticketsByName.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
    }

    public List<String> findMoviesByClient(String clientName) {
        return moviesByClientName.get(clientName);
    }

}
