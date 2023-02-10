package com.theteapottroopers.farmwatch.seeds;

import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.repository.AnimalRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author M.S. Pilat <pilat_m@msn.com>
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
        BiographyReader biographyReader = new BiographyReader();

        String animal1Description = biographyReader.getAnimal1Description();
        String animal2Description = biographyReader.getAnimal2Description();
        String animal3Description = biographyReader.getAnimal3Description();
        String animal4Description = biographyReader.getAnimal4Description();
        String animal5Description = biographyReader.getAnimal5Description();
        String animal6Description = biographyReader.getAnimal6Description();
        String animal7Description = biographyReader.getAnimal7Description();
        String animal8Description = biographyReader.getAnimal8Description();


        Animal animal1 = new Animal("Clara", "chicken", "Galus galus domesticus",
                animal1Description, LocalDate.of(2012, 5, 17),
                "https://upload.wikimedia.org/wikipedia/commons/d/d4/Chicken_portrait.jpg");
        Animal animal2 = new Animal("Benjamin", "donkey", "Equus africanus",
                animal2Description, LocalDate.of(2010, 8, 12),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Donkey_in_Clovelly" +
                        "%2C_North_Devon%2C_England.jpg/440px-Donkey_in_Clovelly%2C_North_Devon%2C_England.jpg");
        Animal animal3 = new Animal("Hugo", "horse", "Equus ferus caballus",
                animal3Description, LocalDate.of(2009, 2, 21),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Horse-and-pony.jpg/" +
                        "1280px-Horse-and-pony.jpg");
        Animal animal4 = new Animal("Patrick", "rabbit", "Oryctolagus cuniculus",
                animal4Description, LocalDate.of(2011, 4, 7),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/Oryctolagus_cuniculus_Rcdo.jpg/" +
                        "440px-Oryctolagus_cuniculus_Rcdo.jpg");
        Animal animal5 = new Animal("Shelly", "turtle", "Emydura subglobosa",
                animal5Description, LocalDate.of(2013, 6, 25),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/" +
                        "Pelomedusa_subrufa_%28cropped%29.JPG/1280px-Pelomedusa_subrufa_%28cropped%29.JPG");
        Animal animal6 = new Animal("Peter", "deer", "Dama dama",
                animal6Description, LocalDate.of(2010, 11, 8),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/" +
                        "Roe_deer_eating_leaves_in_Tuntorp_2.jpg/1280px-Roe_deer_eating_leaves_in_Tuntorp_2.jpg");
        Animal animal7 = new Animal("Brownie", "cow", "Bos taurus",
                animal7Description, LocalDate.of(2008, 12, 15),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b7/S%C3%A4rk%C3%A4nniemi_-_cow.jpg/" +
                        "1280px-S%C3%A4rk%C3%A4nniemi_-_cow.jpg");
        Animal animal8 = new Animal("Jimmy", "chicken", "Galus galus domesticus",
                animal8Description, LocalDate.of(2012, 7, 3),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/84/" +
                        "Male_and_female_chicken_sitting_together.jpg/" +
                        "800px-Male_and_female_chicken_sitting_together.jpg");


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
            if(animalToSeed.isEmpty()){
                animalRepository.save(animal);
            }
        }
    }
}
