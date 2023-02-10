package com.theteapottroopers.farmwatch.mapper;

import com.theteapottroopers.farmwatch.dto.AnimalDto;
import com.theteapottroopers.farmwatch.model.Animal;

/**
 * @author Eelke Mulder
 */
public class AnimalMapper {
    public AnimalDto toAnimalDto(Animal animal){
       return new AnimalDto(
               animal.getId(),
               animal.getName(),
               animal.getCommonName(),
               animal.getSpecies(),
               animal.getDescription(),
               animal.getDateOfBirth(),
               animal.getImageUrl());
    }

    public Animal toAnimal(AnimalDto animalDto) {return new Animal(
            animalDto.getName(),
            animalDto.getCommonName(),
            animalDto.getSpecies(),
            animalDto.getDescription(),
            animalDto.getDateOfBirth(),
            animalDto.getImageUrl());}
}
