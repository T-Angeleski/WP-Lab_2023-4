package mk.ukim.finki.mk.lab.model;

import lombok.Data;

@Data
public class TicketOrder {
    private String movieTitle;
    private String clientName;
    private String clientAddress;
    private Long numberOfTickets;
}
