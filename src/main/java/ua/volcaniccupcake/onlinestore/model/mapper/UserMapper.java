package ua.volcaniccupcake.onlinestore.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.volcaniccupcake.onlinestore.model.dto.UserDTO;
import ua.volcaniccupcake.onlinestore.model.security.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User userDTOToUser(UserDTO dto);
    UserDTO userToUserDTO (User user);

}
