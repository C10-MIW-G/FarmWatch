package com.theteapottroopers.farmwatch.service;

import com.theteapottroopers.farmwatch.dto.AnimalDetailDto;
import com.theteapottroopers.farmwatch.exception.AnimalNotFoundException;
import com.theteapottroopers.farmwatch.exception.FieldHasNoInputException;
import com.theteapottroopers.farmwatch.exception.InputIsToLargeException;
import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Berend  <b.boksma@st.hanze.nl>
 * <p>
 * The service for the Animals
 */

@Service
public class AnimalService {

    public static final int MAX_LENGTH_DESCRIPTION = 1000;
    private final AnimalRepository animalRepository;
    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal findAnimalById(Long id){
        return animalRepository.findById(id).orElseThrow(() -> new AnimalNotFoundException(
                "Animal by id " + id + " was not found!"));
    }

    public List<Animal> findAllAnimals(){
        return animalRepository.findAll();
    }

    public void deleteAnimal(Long id){

        animalRepository.deleteById(id);
    }

    public Animal updateAnimal(AnimalDetailDto animalDetailDto){
        Animal existingAnimal = animalRepository.findById(animalDetailDto.getId()).get();
        existingAnimal.setName(animalDetailDto.getName());
        existingAnimal.setCommonName(animalDetailDto.getCommonName());
        existingAnimal.setSpecies(animalDetailDto.getSpecies());
        existingAnimal.setDescription(animalDetailDto.getDescription());
        existingAnimal.setDateOfBirth(animalDetailDto.getDateOfBirth());
        existingAnimal.setImageUrl(animalDetailDto.getImageUrl());
        Animal updatedAnimal = animalRepository.save(existingAnimal);
        return updatedAnimal;
    }

    public void addAnimal(Animal animal){
        validationCheck(animal);
        animalRepository.save(animal);
    }

    private static void validationCheck(Animal animal) {
        if (animal.getName().length() == 0){
            throw new FieldHasNoInputException("You have to fill in the animals name");
        }
        if (animal.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new DateTimeException("The date should be in de past");
        }
        if (animal.getDescription().length() > MAX_LENGTH_DESCRIPTION) {
            throw new InputIsToLargeException("Your input is to long");
        }
    }
}
