package cat.lasalle.server.model;

import cat.lasalle.commons.ticket.PriorityDTO;
import cat.lasalle.commons.ticket.ReplyDTO;
import cat.lasalle.commons.ticket.StatusDTO;
import cat.lasalle.commons.user.UserDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Getter @Setter @Document(collection = "tickets") public class Ticket {
    @Id private final String id;
    @CreatedDate private Date creationDate;
    private final String subject;
    private Set<UserDTO> assignees = new HashSet<>();
    private Set<UserDTO> collaborators = new HashSet<>();
    private List<ReplyDTO> messages = new ArrayList<>();
    private String description;
    private StatusDTO status;
    private PriorityDTO priority;
    private Date lastUpdated;
    private UserDTO requester;

    public Ticket(String id, String subject) {
        this.id = id;
        this.subject = subject;
    }

}
