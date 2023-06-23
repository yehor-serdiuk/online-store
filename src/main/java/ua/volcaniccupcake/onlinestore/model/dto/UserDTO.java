package ua.volcaniccupcake.onlinestore.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String password;
    private String username;
}
