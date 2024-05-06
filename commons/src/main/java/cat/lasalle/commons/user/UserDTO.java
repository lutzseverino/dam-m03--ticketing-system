package cat.lasalle.commons.user;

import cat.lasalle.commons.ticket.TicketDTO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter public class UserDTO {
    private String id;
    private TicketDTO ticket;
}
