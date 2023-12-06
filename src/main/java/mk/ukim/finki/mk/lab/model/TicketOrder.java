package mk.ukim.finki.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numberOfTickets;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Movie movie;

    @ManyToOne
    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    private LocalDateTime dateCreated;

    public TicketOrder(Long numberOfTickets, Movie movie, User user) {
        this.numberOfTickets = numberOfTickets;
        this.movie = movie;
        this.user = user;
    }

    public TicketOrder(Long numTickets, Movie movie, User user, LocalDateTime dateCreated) {
        this.numberOfTickets = numTickets;
        this.movie = movie;
        this.user = user;
        this.dateCreated = dateCreated;
    }
}
