package ua.volcaniccupcake.onlinestore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="sneakers")
@Table(name="sneakers")
@Data
public class Sneakers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /*@JsonIgnore
    @ManyToOne
    @JoinColumn(name="country_id")
    Country country;*/
}