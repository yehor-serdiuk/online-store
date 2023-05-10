package ua.volcaniccupcake.onlinestore.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_item",
    joinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")},
    inverseJoinColumns = {@JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")})
    private Set<Item> items;


}
