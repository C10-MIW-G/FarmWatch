package com.theteapottroopers.farmwatch.resource;

import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Berend <b.boksma@st.hanze.nl>
 * <p>
 * This is the resource for the animal.
 */

@CrossOrigin (origins = "http://localhost:4200")
@RestController
@RequestMapping("/animal")
public class AnimalResource {

    private final AnimalService animalService;

    public AnimalResource(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Animal>> getAllAnimals(){
        List<Animal> animals = animalService.findAllAnimals();

        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    @GetMapping("/find/{uuid}")
    public ResponseEntity<Animal> getAnimalByUuid(@PathVariable("uuid") String uuid){
        Animal animal = animalService.findAnimalByUuid(uuid);
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }
}
