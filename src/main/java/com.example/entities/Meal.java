package com.example.entities;


import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "meal")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Meal {
    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "name")
    private String name;

}
