package cat.lasalle.server.model.mapper;

import cat.lasalle.commons.ticket.ReplyDTO;
import cat.lasalle.server.model.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {TicketMapper.class})
public interface ReplyMapper {
    ReplyMapper INSTANCE = Mappers.getMapper(ReplyMapper.class);

    Reply mapToEntity(ReplyDTO dto);

    ReplyDTO mapToDTO(Reply entity);
}
