package com.theteapottroopers.farmwatch.resource;

import com.theteapottroopers.farmwatch.dto.AnimalDto;
import com.theteapottroopers.farmwatch.mapper.AnimalMapper;
import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.repository.AnimalRepository;
import com.theteapottroopers.farmwatch.seeds.AnimalSeeder;
import com.theteapottroopers.farmwatch.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    private final AnimalRepository animalRepository;
    private final AnimalService animalService;
    private final AnimalMapper animalMapper;

    public AnimalResource(AnimalService animalService, AnimalRepository animalRepository) {
        this.animalService = animalService;
        this.animalRepository = animalRepository;
        animalMapper = new AnimalMapper();
    }
    
    @GetMapping()
    public ResponseEntity<List<AnimalDto>> getAllAnimals(){
        List<Animal> animals = animalService.findAllAnimals();
        List<AnimalDto> animalDtos = new ArrayList<>();
        for (Animal animal: animals) {
            animalDtos.add(animalMapper.toAnimalDto(animal));
        }
        return new ResponseEntity<>(animalDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDto> getAnimalById(@PathVariable("id") Long id){
        AnimalDto animalDto = animalMapper.toAnimalDto(animalService.findAnimalById(id));
        return new ResponseEntity<>(animalDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAnimalById(@PathVariable("id") Long id){
        animalService.deleteAnimal(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addAnimal(@RequestBody AnimalDto animalDto){
        animalService.addAnimal(animalMapper.toAnimal(animalDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/seed")
    public ResponseEntity<?> seedAnimals(){
        animalService.seedAnimals();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
