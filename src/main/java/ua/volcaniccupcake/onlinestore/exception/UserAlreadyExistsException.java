package ua.volcaniccupcake.onlinestore.exception;

import ua.volcaniccupcake.onlinestore.model.dto.UserDTO;

public class UserAlreadyExistsException extends RuntimeException {
    private UserDTO userDTO;
    public UserAlreadyExistsException(String message) {
        super(message);
    }
    public UserAlreadyExistsException(UserDTO userDTO) {
        this("User " + userDTO + " already exists");
        this.userDTO = userDTO;
    }
}
