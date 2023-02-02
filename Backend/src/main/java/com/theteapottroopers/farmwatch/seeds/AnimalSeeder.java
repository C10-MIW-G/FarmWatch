package com.theteapottroopers.farmwatch.seeds;

import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.repository.AnimalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @Author: M.S. Pilat <pilat_m@msn.com>
 * <p>
 * dit programma doet iets
 */

public class AnimalSeeder {

    private final AnimalRepository animalRepository;

    public AnimalSeeder(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public void SeedAnimals() {

        List<Animal> animalsToSeed = new ArrayList<>();

        Animal animal1 = new Animal("Clara de Kip");
        Animal animal2 = new Animal("Benjamin de Ezel");
        Animal animal3 = new Animal("Hugo het Paard");
        Animal animal4 = new Animal("Patrick het Konijn");
        Animal animal5 = new Animal("Shelly het Schildpad");
        Animal animal6 = new Animal("Peter het Hert");
        Animal animal7 = new Animal("Brownie de Koe");
        Animal animal8 = new Animal("Jimmy de Haan");

        animalsToSeed.add(animal1);
        animalsToSeed.add(animal2);
        animalsToSeed.add(animal3);
        animalsToSeed.add(animal4);
        animalsToSeed.add(animal5);
        animalsToSeed.add(animal6);
        animalsToSeed.add(animal7);
        animalsToSeed.add(animal8);

        for (Animal animal : animalsToSeed) {
            Optional<Animal> animalToSeed = animalRepository.findAnimalByName(animal.getName());
            if(!animalToSeed.isPresent()){
                animalRepository.save(animal);
            }
        }
    }

}
