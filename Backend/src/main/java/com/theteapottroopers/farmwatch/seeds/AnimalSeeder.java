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

        Animal animal1 = new Animal("0001", "Kip");
        Animal animal2 = new Animal("0002", "Ezel");
        Animal animal3 = new Animal("0003", "Paard");
        Animal animal4 = new Animal("0004", "Konijn");
        Animal animal5 = new Animal("0005", "Schildpad");
        Animal animal6 = new Animal("0006", "Hert");
        Animal animal7 = new Animal("0007", "Koe");
        Animal animal8 = new Animal("0008", "Haan");

        animalsToSeed.add(animal1);
        animalsToSeed.add(animal2);
        animalsToSeed.add(animal3);
        animalsToSeed.add(animal4);
        animalsToSeed.add(animal5);
        animalsToSeed.add(animal6);
        animalsToSeed.add(animal7);
        animalsToSeed.add(animal8);

        for (Animal animal : animalsToSeed) {
            Optional<Animal> animalToSeed= animalRepository.findAnimalByUuid(animal.getUuid());
            if(!animalToSeed.isPresent()){
                animalRepository.save(animal);
            }
        }
    }

}
