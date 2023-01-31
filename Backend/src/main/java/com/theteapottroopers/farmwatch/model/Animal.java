package com.theteapottroopers.farmwatch.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 * @author Berend <b.boksma@st.hanze.nl>
 * <p>
 * This class contains data about a specific animal
 */

@Entity
@Getter
@Setter
public class Animal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Animal() {
    }

    public Animal(String name) {
        this.name = name;
    }
}
