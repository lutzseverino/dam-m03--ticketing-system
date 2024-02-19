package cat.lasalle.server.model.mapper;

import cat.lasalle.commons.ticket.TicketDTO;
import cat.lasalle.server.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    Ticket mapToEntity(TicketDTO dto);

    TicketDTO mapToDTO(Ticket entity);
}
