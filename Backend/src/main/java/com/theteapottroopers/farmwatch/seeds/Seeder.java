package com.theteapottroopers.farmwatch.seeds;

import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.model.ticket.Ticket;
import com.theteapottroopers.farmwatch.model.ticket.TicketStatus;
import com.theteapottroopers.farmwatch.repository.AnimalRepository;
import com.theteapottroopers.farmwatch.repository.TicketRepository;
import com.theteapottroopers.farmwatch.repository.UserRepository;
import com.theteapottroopers.farmwatch.security.user.Role;
import com.theteapottroopers.farmwatch.security.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author M.S. Pilat <pilat_m@msn.com>
 * <p>
 * dit programma doet iets
 */

public class Seeder {
    private final AnimalRepository animalRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Seeder(AnimalRepository animalRepository, TicketRepository ticketRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.animalRepository = animalRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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


        User user1 = User.builder()
                .firstname("Kai")
                .lastname("Patel")
                .email("kaipatel@mail.com")
                .username("user")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_ADMIN)
                .build();
        userRepository.save(user1);

        User user2 = User.builder()
                .firstname("Lila")
                .lastname("Singh")
                .email("lilasingh@mail.com")
                .username("Lila")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user2);

        User user3 = User.builder()
                .firstname("Dante")
                .lastname("Williams")
                .email("dantewilliams@mail.com")
                .username("Dante")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user3);

        User user4 = User.builder()
                .firstname("Isabel")
                .lastname("Garcia")
                .email("isabelgarcia@mail.com")
                .username("Isabel")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user4);

        User user5 = User.builder()
                .firstname("Caleb")
                .lastname("Nguyen")
                .email("calebnguyen@mail.com")
                .username("Caleb")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user5);

        User user6 = User.builder()
                .firstname("Leila")
                .lastname("Kim")
                .email("leilakim@mail.com")
                .username("Leila")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user6);

        User user7 = User.builder()
                .firstname("Ethan")
                .lastname("Chen")
                .email("ethanchen@mail.com")
                .username("Ethan")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_CARETAKER)
                .build();
        userRepository.save(user7);

        User user8 = User.builder()
                .firstname("Avery")
                .lastname("Martin")
                .email("averymartin@mail.com")
                .username("Avery")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_CARETAKER)
                .build();
        userRepository.save(user8);


        Ticket ticket1 = new Ticket(null, "Broken wing", "I was walking through the park and noticed " +
                "that one of the chickens had a broken wing!", TicketStatus.OPEN, animal1, user2, null,
                null);
        ticketRepository.save(ticket1);

        Ticket ticket2 = new Ticket(null, "Limping hoof", "I was at the zoo and noticed that one " +
                "of the donkeys had a limping hoof!", TicketStatus.OPEN, animal2, user3, null, null);
        ticketRepository.save(ticket2);

        Ticket ticket3 = new Ticket(null, "Eye infection", "I saw a rabbit with an eye " +
                "infection in my neighborhood.", TicketStatus.OPEN, animal4, user3, null, null);
        ticketRepository.save(ticket3);

        Ticket ticket4 = new Ticket(null, "Broken antler", "I was hiking and found a deer " +
                "with a broken antler.", TicketStatus.OPEN, animal6, user4, null, null);
        ticketRepository.save(ticket4);

        Ticket ticket5 = new Ticket(null, "Upside down turtle", "I was visiting your " +
                "beautiful park, and I think one of the turtles is upside down!.",
                TicketStatus.OPEN, animal5, user5, null, null);
        ticketRepository.save(ticket5);

        Ticket ticket6 = new Ticket(null, "Limping leg", "I was in the park and saw " +
                "a cow holding it's leg in a weird angle.", TicketStatus.OPEN, animal7, user5,
                null, null);
        ticketRepository.save(ticket6);

        Ticket ticket7 = new Ticket(null, "Scratched eye", "I found a chicken with a " +
                "scratched eye on my balcony.", TicketStatus.OPEN, animal8, user7, null, null);
        ticketRepository.save(ticket7);

        Ticket ticket8 = new Ticket(null, "Bald spots", "I was walking through the park with " +
                "my grandson, and we noticed that one of the horses had some bald spots, did you know that?",
                TicketStatus.OPEN, animal3, user8, null, null);
        ticketRepository.save(ticket8);

    }
}
