package cat.lasalle.server.model.mapper;

import cat.lasalle.commons.user.UserDTO;
import cat.lasalle.server.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {TicketMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapToEntity(UserDTO dto);

    UserDTO mapToDTO(User entity);
}
