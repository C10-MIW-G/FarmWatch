package com.theteapottroopers.farmwatch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Eelke Mulder
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnimalDetailDto {
    private Long id;
    private String name;
    private String commonName;
    private String species;
    private String description;
    private LocalDate dateOfBirth;
    private String imageUrl;
}