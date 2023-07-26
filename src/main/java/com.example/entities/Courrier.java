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
@Table(name = "courrier")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Courrier {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courrier_id_generator")
    @SequenceGenerator(name = "courrier_id_generator", sequenceName = "courrier_id_gen")
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "salary")
    private String salary;

    @JsonIgnore
    @OneToMany(mappedBy = "courrier",cascade = CascadeType.REMOVE)
    private Set<Order> orders;
}
