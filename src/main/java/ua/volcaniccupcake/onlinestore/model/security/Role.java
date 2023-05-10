package ua.volcaniccupcake.onlinestore.model.security;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Singular
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_authority",
    joinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")},
    inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private Set<Authority> authorities;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<Authority> users;
}
