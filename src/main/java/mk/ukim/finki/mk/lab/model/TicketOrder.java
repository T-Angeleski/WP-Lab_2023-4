package mk.ukim.finki.mk.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String movieTitle;
    private Long numberOfTickets;

    public TicketOrder(String movieTitle, Long numberOfTickets) {
        this.movieTitle = movieTitle;
        this.numberOfTickets = numberOfTickets;
    }

}
