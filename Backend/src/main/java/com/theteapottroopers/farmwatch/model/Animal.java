package com.theteapottroopers.farmwatch.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Berend <b.boksma@st.hanze.nl>
 * <p>
 * This class contains data about a specific animal
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Animal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String name;
    private String commonName;
    private String species;
    @Column(length=1000)
    private String description;
    @PastOrPresent
    private LocalDate dateOfBirth;
    @ManyToOne
    private ImageData imageData;

    public Animal(String name, String commonName, String species, String description, LocalDate dateOfBirth, ImageData imageData) {
        this.name = name;
        this.commonName = commonName;
        this.species = species;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.imageData = imageData;
    }
}
