package com.theteapottroopers.farmwatch.dto;

import lombok.*;

import java.time.LocalDate;

/**
 * @author Eelke Mulder
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AnimalDetailDto {
    private Long id;
    private String name;
    private String commonName;
    private String species;
    private String description;
    private LocalDate dateOfBirth;
    private String imageFileName;
}