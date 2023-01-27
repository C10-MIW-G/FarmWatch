package com.theteapottroopers.farmwatch.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private String uuid;

    private String name;

    public Animal() {
    }

    public Animal(String uuid, String name) {
        this.id = id;
        this.name = name;
        this.uuid = uuid;
    }
}
