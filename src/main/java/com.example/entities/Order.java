package com.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "courrier_id", nullable = false)
    private Courrier courrier;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
