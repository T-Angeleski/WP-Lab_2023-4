package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.TicketOrder;
import org.springframework.stereotype.Service;

@Service
public interface TicketOrderService {
    TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets);
}
