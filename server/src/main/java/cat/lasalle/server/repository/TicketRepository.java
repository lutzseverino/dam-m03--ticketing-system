package cat.lasalle.server.repository;

import cat.lasalle.server.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TicketRepository extends JpaRepository<Ticket, String> {
}
