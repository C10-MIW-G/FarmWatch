package com.theteapottroopers.farmwatch.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

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
        this.id = id;
        this.name = name;
    }
}
