package ua.volcaniccupcake.onlinestore.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.volcaniccupcake.onlinestore.model.dto.UserDTO;
import ua.volcaniccupcake.onlinestore.model.security.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(source = "phoneNumber", target = "customer.phoneNumber")
    @Mapping(source = "name", target = "customer.name")
    @Mapping(source = "email", target = "customer.email")
    User userDTOToUser(UserDTO dto);

}
