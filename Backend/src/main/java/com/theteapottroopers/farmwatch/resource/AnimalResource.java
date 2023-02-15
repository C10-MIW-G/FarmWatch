package com.theteapottroopers.farmwatch.resource;

import com.theteapottroopers.farmwatch.dto.AnimalDetailDto;
import com.theteapottroopers.farmwatch.dto.AnimalOverviewDto;
import com.theteapottroopers.farmwatch.mapper.AnimalMapper;
import com.theteapottroopers.farmwatch.model.Animal;
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

@CrossOrigin (origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/animal")
public class AnimalResource {
    private final AnimalService animalService;
    private final AnimalMapper animalMapper;

    public AnimalResource(AnimalService animalService) {
        this.animalService = animalService;
        animalMapper = new AnimalMapper();
    }
    
    @GetMapping()
    public ResponseEntity<List<AnimalOverviewDto>> getAllAnimals(){
        List<Animal> animals = animalService.findAllAnimals();
        List<AnimalOverviewDto> animalOverviewDtos = new ArrayList<>();
        for (Animal animal: animals) {
            animalOverviewDtos.add(animalMapper.toAnimalOverviewDto(animal));
        }
        return new ResponseEntity<>(animalOverviewDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDetailDto> getAnimalById(@PathVariable("id") Long id){
        AnimalDetailDto animalDetailDto = animalMapper.toAnimalDetailDto(animalService.findAnimalById(id));
        return new ResponseEntity<>(animalDetailDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAnimalById(@PathVariable("id") Long id){
        animalService.deleteAnimal(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AnimalDetailDto> updateAnimal(@RequestBody AnimalDetailDto animalDetailDto){
        Animal updateAnimal = animalService.updateAnimal(animalDetailDto);
        AnimalDetailDto updateAnimalDetailDto = animalMapper.toAnimalDetailDto(updateAnimal);
        return new ResponseEntity<>(updateAnimalDetailDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addAnimal(@RequestBody AnimalDetailDto animalDetailDto){
        animalService.addAnimal(animalMapper.toAnimal(animalDetailDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
