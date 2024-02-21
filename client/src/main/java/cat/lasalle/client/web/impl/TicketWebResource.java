package cat.lasalle.client.web.impl;

import cat.lasalle.client.web.WebResource;
import cat.lasalle.commons.ticket.TicketDTO;

public class TicketWebResource extends WebResource<TicketDTO> {
    protected TicketWebResource() {
        super(TicketDTO.class, "ticket");
    }
}
