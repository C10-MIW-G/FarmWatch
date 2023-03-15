package com.theteapottroopers.farmwatch.mapper;

import com.theteapottroopers.farmwatch.dto.AnimalDetailDto;
import com.theteapottroopers.farmwatch.dto.AnimalOverviewDto;
import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.service.FileStorageService;

/**
 * @author Eelke Mulder
 */
public class AnimalMapper extends Mapper{
    private final FileStorageService fileStorageService;

    public AnimalMapper(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    public AnimalOverviewDto toAnimalOverviewDto(Animal animal){
       return new AnimalOverviewDto(
               animal.getId(),
               animal.getName(),
               animal.getCommonName(),
               animal.getImageData() != null ? animal.getImageData().getName() : null,
               animal.getTicketAmount());

    }

    public AnimalDetailDto toAnimalDetailDto(Animal animal){
        return new AnimalDetailDto(
                animal.getId(),
                animal.getName(),
                animal.getCommonName(),
                animal.getSpecies(),
                animal.getDescription(),
                animal.getDateOfBirth(),
                animal.getImageData() != null ? animal.getImageData().getName() : null
        );
    }

    public Animal toAnimal(AnimalDetailDto animalDetailDto) {return new Animal(
            emptyToNull(animalDetailDto.getName()),
            emptyToNull(animalDetailDto.getCommonName()),
            emptyToNull(animalDetailDto.getSpecies()),
            emptyToNull(animalDetailDto.getDescription()),
            animalDetailDto.getDateOfBirth(),
            animalDetailDto.getImageFileName() != null ?
                    fileStorageService.findImageDataByFileName(animalDetailDto.getImageFileName()) : null);
            }
}
