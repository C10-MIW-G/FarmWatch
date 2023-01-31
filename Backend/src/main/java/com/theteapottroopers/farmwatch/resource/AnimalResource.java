package com.theteapottroopers.farmwatch.resource;

import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.repository.AnimalRepository;
import com.theteapottroopers.farmwatch.seeds.AnimalSeeder;
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
    private final AnimalRepository animalRepository;

    public AnimalResource(AnimalService animalService, AnimalRepository animalRepository) {
        this.animalService = animalService;
        this.animalRepository = animalRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Animal>> getAllAnimals(){
        List<Animal> animals = animalService.findAllAnimals();

        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable("id") Long id){
        Animal animal = animalService.findAnimalById(id);
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    @PostMapping("/seed")
    public ResponseEntity<?> seedAnimals(){

        AnimalSeeder animalSeeder = new AnimalSeeder(animalRepository);
        animalSeeder.SeedAnimals();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
