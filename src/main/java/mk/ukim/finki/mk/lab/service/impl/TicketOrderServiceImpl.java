package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.TicketOrder;
import mk.ukim.finki.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {

    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets) {
        return new TicketOrder(movieTitle, clientName, address, (long) numberOfTickets);
    }
}
