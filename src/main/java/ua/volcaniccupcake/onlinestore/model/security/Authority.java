package ua.volcaniccupcake.onlinestore.model.security;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String permission;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> roles;
}