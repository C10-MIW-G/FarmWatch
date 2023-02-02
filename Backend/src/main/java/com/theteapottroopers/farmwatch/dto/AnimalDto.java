package com.theteapottroopers.farmwatch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Eelke Mulder
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnimalDto {

    private Long id;
    private String name;

    public AnimalDto(String name) {
        this.name = name;
    }
}
