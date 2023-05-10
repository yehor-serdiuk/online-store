package ua.volcaniccupcake.onlinestore.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToMany(mappedBy = "items", fetch = FetchType.EAGER)
    private Set<Order> orders;
}
