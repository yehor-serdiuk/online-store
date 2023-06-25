package ua.volcaniccupcake.onlinestore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.volcaniccupcake.onlinestore.model.dto.UserDTO;
import ua.volcaniccupcake.onlinestore.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "This REST controller provides services to manage product in the Online Store Application")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registers a new user")
    public void registerNewUser(@RequestBody @Valid UserDTO userDTO) {
        userService.registerNewUser(userDTO);
    }
}
