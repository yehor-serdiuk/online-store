package ua.volcaniccupcake.onlinestore.model;

import jakarta.persistence.*;
import lombok.*;
import ua.volcaniccupcake.onlinestore.model.security.User;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNumber;

    private String email;


    @OneToOne(mappedBy = "customer", fetch = FetchType.EAGER)
    private User user;

    @Singular
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Order> orders;
}
