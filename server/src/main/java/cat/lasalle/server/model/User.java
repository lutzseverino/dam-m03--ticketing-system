package cat.lasalle.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity public class User {
    @Id private String id;

    @ManyToOne
    @JoinTable(
            name = "ticket_assignee",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    private Ticket ticket;
}