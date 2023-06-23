package ua.volcaniccupcake.onlinestore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.volcaniccupcake.onlinestore.model.dto.UserDTO;
import ua.volcaniccupcake.onlinestore.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewUser(@RequestBody UserDTO userDTO) {
        userService.registerNewUser(userDTO);
    }
}
