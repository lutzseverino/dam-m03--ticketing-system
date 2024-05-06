package cat.lasalle.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity @Table(name = "reply")
public class Reply {
    @Id private String id;

    @ManyToOne
    @JoinColumn(name = "ticket_id") private Ticket ticket;

    private String message;
}