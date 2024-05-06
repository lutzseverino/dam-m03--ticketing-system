package cat.lasalle.server.model;

import cat.lasalle.commons.ticket.PriorityDTO;
import cat.lasalle.commons.ticket.StatusDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.*;

@Getter @Setter
@Entity public class Ticket {
    private final String subject;
    @Id private String id;
    @CreatedDate private Date creationDate;

    @OneToMany(mappedBy = "ticket")
    private Set<User> assignees = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "ticket_collaborator",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> collaborators = new HashSet<>();

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> messages = new ArrayList<>();

    private String description;
    private StatusDTO status;
    private PriorityDTO priority;
    private Date lastUpdated;
    @ManyToOne private User requester;

    public Ticket(String id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    public Ticket() {
        this.id = UUID.randomUUID().toString();
        this.subject = "";
    }
}
