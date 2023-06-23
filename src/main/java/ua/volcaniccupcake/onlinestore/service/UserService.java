package ua.volcaniccupcake.onlinestore.service;

import ua.volcaniccupcake.onlinestore.model.dto.UserDTO;

public interface UserService {
    void registerNewUser(UserDTO userDTO);
}
