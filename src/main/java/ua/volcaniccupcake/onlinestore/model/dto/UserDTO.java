package ua.volcaniccupcake.onlinestore.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.mapstruct.Mapping;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Min(2)
    @Max(32)
    private String username;

    @Min(8)
    @Max(32)
    private String password;

    @Min(8)
    @Max(32)
    private String repeatPassword;
}
