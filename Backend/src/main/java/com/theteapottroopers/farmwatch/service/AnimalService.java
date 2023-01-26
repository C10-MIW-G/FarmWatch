package com.theteapottroopers.farmwatch.service;

import com.theteapottroopers.farmwatch.exception.AnimalNotFoundException;
import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Berend  <b.boksma@st.hanze.nl>
 * <p>
 * The service for the Animals
 */

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal findAnimalByUuid(String uuid){
        return animalRepository.findAnimalByUuid(uuid).orElseThrow(() -> new AnimalNotFoundException(
                "Animal by id " + uuid + " was not found!"));
    }

    public List<Animal> findAllAnimals(){
        return animalRepository.findAll();
    }

}
