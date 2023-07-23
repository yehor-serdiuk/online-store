package ua.volcaniccupcake.onlinestore.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.mapstruct.Mapping;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Size(min = 2, max = 32)
    private String username;

    @Size(min = 8, max = 32)
    private String password;

    @Size(min = 8, max = 32)
    private String repeatPassword;

    @Size(min = 8, max = 32)
    private String phoneNumber;

    @Size(min = 1, max = 64)
    private String name;

    @Email
    private String email;
}
