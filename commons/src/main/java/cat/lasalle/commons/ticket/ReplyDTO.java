package cat.lasalle.commons.ticket;

import cat.lasalle.commons.user.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class ReplyDTO {
    private final long id;
    private final List<cat.lasalle.commons.ticket.ReplyDTO> replies = new ArrayList<>();
    @Setter
    private UserDTO sender;
    @Setter
    private Date sentDate;
    @Setter
    private String body;

    public ReplyDTO(long id) {
        this.id = id;
    }
}
