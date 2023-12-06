package mk.ukim.finki.mk.lab.repository.jpa;

import mk.ukim.finki.mk.lab.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaTicketOrderRepository extends JpaRepository<TicketOrder, Long> {

    // TODO: extra baranja
    List<TicketOrder> findAllByUserId(Long userId);

    List<TicketOrder> findAllByDateCreatedBetween(LocalDateTime from, LocalDateTime to);
}
