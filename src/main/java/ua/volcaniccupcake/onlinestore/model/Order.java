package ua.volcaniccupcake.onlinestore.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "\"ORDER\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Singular
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<Item> items;
}
