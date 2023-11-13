package mk.ukim.finki.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.mk.lab.model.TicketOrder;
import mk.ukim.finki.mk.lab.repository.TicketOrderRepository;
import mk.ukim.finki.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketOrderServiceImpl implements TicketOrderService {

    private final TicketOrderRepository ticketOrderRepository;

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
}
