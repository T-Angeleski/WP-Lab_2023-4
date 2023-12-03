package mk.ukim.finki.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime dateCreated;

    @OneToMany
    private List<TicketOrder> ticketOrders;

    public ShoppingCart(User user, LocalDateTime dateCreated, List<TicketOrder> ticketOrders) {
        this.user = user;
        this.dateCreated = dateCreated;
        this.ticketOrders = ticketOrders;
    }
}
