package cat.lasalle.server.repository;

import cat.lasalle.commons.ticket.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {
}
