package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.TicketOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface TicketOrderService {
    TicketOrder placeOrder(String movieTitle, String clientName, String address, long numberOfTickets);

    List<TicketOrder> findAll();

    Map.Entry<String, Long> findMostPopular();

}
