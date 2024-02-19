package cat.lasalle.commons.ticket;

import cat.lasalle.commons.user.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter @Setter public class TicketDTO {
    private String id;
    private final Date creationDate = new Date();
    private final String subject;
    private final Set<UserDTO> assignees = new HashSet<>();
    private final Set<UserDTO> collaborators = new HashSet<>();
    private final List<ReplyDTO> messages = new ArrayList<>();
    private String description;
    private StatusDTO status;
    private PriorityDTO priority;
    private Date lastUpdated;
    private UserDTO requester;

    public TicketDTO(String subject) {
        this.subject = subject;
    }
}
