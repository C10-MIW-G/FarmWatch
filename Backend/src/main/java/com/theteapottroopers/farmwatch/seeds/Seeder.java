package com.theteapottroopers.farmwatch.seeds;

import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.model.ImageData;
import com.theteapottroopers.farmwatch.model.ticket.Ticket;
import com.theteapottroopers.farmwatch.model.ticket.TicketStatus;
import com.theteapottroopers.farmwatch.repository.AnimalRepository;
import com.theteapottroopers.farmwatch.repository.StorageRepository;
import com.theteapottroopers.farmwatch.repository.TicketRepository;
import com.theteapottroopers.farmwatch.repository.UserRepository;
import com.theteapottroopers.farmwatch.security.user.Role;
import com.theteapottroopers.farmwatch.security.user.User;
import com.theteapottroopers.farmwatch.service.FileStorageService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
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
    private final StorageRepository storageRepository;

    public Seeder(AnimalRepository animalRepository, TicketRepository ticketRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, StorageRepository storageRepository) {
        this.animalRepository = animalRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.storageRepository = storageRepository;
    }

    public void SeedAnimals() throws IOException {
        ImageReader imageReader = new ImageReader(storageRepository);



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
        String animal9Description = biographyReader.getAnimal9Description();
        String animal10Description = biographyReader.getAnimal10Description();
        String animal11Description = biographyReader.getAnimal11Description();
        String animal12Description = biographyReader.getAnimal12Description();
        String animal13Description = biographyReader.getAnimal13Description();
        String animal14Description = biographyReader.getAnimal14Description();
        String animal15Description = biographyReader.getAnimal15Description();
        String animal16Description = biographyReader.getAnimal16Description();
        String animal17Description = biographyReader.getAnimal17Description();
        String animal18Description = biographyReader.getAnimal18Description();
        String animal19Description = biographyReader.getAnimal19Description();
        String animal20Description = biographyReader.getAnimal20Description();
        String animal21Description = biographyReader.getAnimal21Description();
        String animal22Description = biographyReader.getAnimal22Description();
        String animal23Description = biographyReader.getAnimal23Description();
        String animal24Description = biographyReader.getAnimal24Description();
        String animal25Description = biographyReader.getAnimal25Description();
        String animal26Description = biographyReader.getAnimal26Description();
        String animal27Description = biographyReader.getAnimal27Description();


        Animal animal1 = new Animal("Napoleon", "Pig", "Sus scrofa domesticus",
                animal1Description, LocalDate.of(2018, 5, 17), null);

        Animal animal2 = new Animal("Butterscotch", "Pig", "Sus scrofa domesticus",
                animal2Description, LocalDate.of(2016, 8, 12), null);

        Animal animal3 = new Animal("Oreo", "Pig", "Sus scrofa domesticus",
                animal3Description, LocalDate.of(2015, 2, 21), null);

        Animal animal4 = new Animal("Pickles", "Pig", "Sus scrofa domesticus",
                animal4Description, LocalDate.of(2017, 4, 7), null);

        Animal animal5 = new Animal("Clara", "Chicken", "Galus galus domesticus",
                animal5Description, LocalDate.of(2019, 6, 25), null);

        Animal animal6 = new Animal("Dora", "Chicken", "Galus galus domesticus",
                animal6Description, LocalDate.of(2014, 11, 8), null);

        Animal animal7 = new Animal("BigBird", "Chicken", "Galus galus domesticus",
                animal7Description, LocalDate.of(2012, 12, 15), null);

        Animal animal8 = new Animal("Snowball", "Chicken", "Galus galus silkus",
                animal8Description, LocalDate.of(2018, 7, 3), null);

        Animal animal9 = new Animal("Penguin", "Chicken", "Galus galus domesticus",
                animal9Description, LocalDate.of(2017, 4, 15), null);

        Animal animal10= new Animal("Elvis", "Chicken", "Galus galus domesticus",
                animal10Description, LocalDate.of(2019, 2, 3), null);

        Animal animal11= new Animal("Florence", "Chicken", "Galus galus domesticus",
                animal11Description, LocalDate.of(2020, 7, 30), null);

        Animal animal12= new Animal("Frank", "Chicken", "Galus galus domesticus",
                animal12Description, LocalDate.of(2013, 3, 1), null);

        Animal animal13= new Animal("Jasmin", "Peacock", "Pavo cristatus",
                animal13Description, LocalDate.of(2015, 9, 20), null);

        Animal animal14= new Animal("Nimbus", "Peacock", "Pavo cristatus",
                animal14Description, LocalDate.of(2018, 6, 1), null);

        Animal animal15= new Animal("Sugar", "Pony", "Equus caballus",
                animal15Description, LocalDate.of(2016, 1, 10), null);

        Animal animal16= new Animal("Clip-Clop", "Pony", "Equus caballus",
                animal16Description, LocalDate.of(2017, 8, 5), null);

        Animal animal17= new Animal("Chief", "Horse", "Equus ferus caballus",
                animal17Description, LocalDate.of(2014, 4, 25), null);

        Animal animal18= new Animal("Floppy", "Rabbit", "Pentalagus furnessi",
                animal18Description, LocalDate.of(2015, 11, 3), null);

        Animal animal19= new Animal("Jumper", "Rabbit", "Pentalagus furnessi",
                animal19Description, LocalDate.of(2019, 5, 12), null);

        Animal animal20= new Animal("Patrick", "Rabbit", "Pentalagus furnessi",
                animal20Description, LocalDate.of(2019, 5, 12), null);

        Animal animal21= new Animal("Washington", "Sheep", "Ovis aries",
                animal21Description, LocalDate.of(2017, 1, 23), null);

        Animal animal22= new Animal("Jasper", "Sheep", "Ovis aries",
                animal22Description, LocalDate.of(2017, 1, 23), null);

        Animal animal23= new Animal("G.O.A.T", "Goat", "Capra hircus",
                animal23Description, LocalDate.of(2018, 9, 8), null);

        Animal animal24= new Animal("Opie", "Goat", "Capra hircus",
                animal24Description, LocalDate.of(2018, 9, 8), null);

        Animal animal25= new Animal("Benjamin", "Donkey", "Equus africanus asinus",
                animal25Description, LocalDate.of(2015, 11, 2), null);

        Animal animal26= new Animal("Peter", "Deer", "Dama dama",
                animal26Description, LocalDate.of(2016, 7, 14), null);

        Animal animal27= new Animal("Brownie", "Cow", "Bos taurus",
                animal27Description, LocalDate.of(2014, 12, 9), null);








        animalsToSeed.add(animal1);
        animalsToSeed.add(animal2);
        animalsToSeed.add(animal3);
        animalsToSeed.add(animal4);
        animalsToSeed.add(animal5);
        animalsToSeed.add(animal6);
        animalsToSeed.add(animal7);
        animalsToSeed.add(animal8);
        animalsToSeed.add(animal9);
        animalsToSeed.add(animal10);
        animalsToSeed.add(animal11);
        animalsToSeed.add(animal12);
        animalsToSeed.add(animal13);
        animalsToSeed.add(animal14);
        animalsToSeed.add(animal15);
        animalsToSeed.add(animal16);
        animalsToSeed.add(animal17);
        animalsToSeed.add(animal18);
        animalsToSeed.add(animal19);
        animalsToSeed.add(animal20);
        animalsToSeed.add(animal21);
        animalsToSeed.add(animal22);
        animalsToSeed.add(animal23);
        animalsToSeed.add(animal24);
        animalsToSeed.add(animal25);
        animalsToSeed.add(animal26);
        animalsToSeed.add(animal27);

        for (Animal animal : animalsToSeed) {
            Optional<Animal> animalToSeed = animalRepository.findAnimalByName(animal.getName());
            if(animalToSeed.isEmpty()){
                imageReader.saveImageDataFromPath("src/main/resources/images/"
                        + animal.getName() + ".jpg", storageRepository);
                Optional<ImageData> optionalImage = storageRepository.findByName(animal.getName() + ".jpg");
                animal.setImageData( optionalImage.get());
                animalRepository.save(animal);
            }
        }


        User user1 = User.builder()
                .fullName("Kai Patel")
                .email("kaipatel@mail.com")
                .username("user")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_ADMIN)
                .verified(true)
                .build();
        userRepository.save(user1);

        User user2 = User.builder()
                .fullName("Lila Singh")
                .email("lilasingh@mail.com")
                .username("Lila")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_USER)
                .verified(true)
                .build();
        userRepository.save(user2);

        User user3 = User.builder()
                .fullName("Dante Williams")
                .email("dantewilliams@mail.com")
                .username("Dante")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_USER)
                .verified(true)
                .build();
        userRepository.save(user3);

        User user4 = User.builder()
                .fullName("Isabel Garcia")
                .email("isabelgarcia@mail.com")
                .username("Isabel")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_USER)
                .verified(true)
                .build();
        userRepository.save(user4);

        User user5 = User.builder()
                .fullName("Caleb Nguyen")
                .email("calebnguyen@mail.com")
                .username("Caleb")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_USER)
                .verified(true)
                .build();
        userRepository.save(user5);

        User user6 = User.builder()
                .fullName("Leila Kim")
                .email("leilakim@mail.com")
                .username("Leila")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_USER)
                .verified(true)
                .build();
        userRepository.save(user6);

        User user7 = User.builder()
                .fullName("Ethan Chen")
                .email("ethanchen@mail.com")
                .username("Ethan")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_CARETAKER)
                .verified(true)
                .build();
        userRepository.save(user7);

        User user8 = User.builder()
                .fullName("Avery Martin")
                .email("averymartin@mail.com")
                .username("Avery")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_CARETAKER)
                .verified(true)
                .build();
        userRepository.save(user8);


        Ticket ticket1 = new Ticket(null, "Broken wing", "I was walking through the park and noticed " +
                "that one of the chickens had a broken wing!", TicketStatus.OPEN, animal5, user2, user8,
                null, null);
        ticketRepository.save(ticket1);

        Ticket ticket2 = new Ticket(null, "Limping hoof", "I was at the zoo and noticed that one " +
                "of the donkeys had a limping hoof!", TicketStatus.OPEN, animal25, user3, user7, null, null);
        ticketRepository.save(ticket2);

        Ticket ticket3 = new Ticket(null, "Eye infection", "I saw a rabbit with an eye " +
                "infection in my neighborhood.", TicketStatus.OPEN, animal18, user3, user8, null, null);
        ticketRepository.save(ticket3);

        Ticket ticket4 = new Ticket(null, "Broken antler", "I was hiking and found a deer " +
                "with a broken antler.", TicketStatus.OPEN, animal26, user4, user7, null, null);
        ticketRepository.save(ticket4);

        Ticket ticket5 = new Ticket(null, "Sheep stuck in fence", "I was visiting your " +
                "beautiful park, and I think one of the sheep is stuck in the fence!.",
                TicketStatus.OPEN, animal21, user5, user8, null, null);
        ticketRepository.save(ticket5);

        Ticket ticket6 = new Ticket(null, "Limping leg", "I was in the park and saw " +
                "a cow holding it's leg in a weird angle.", TicketStatus.OPEN, animal27, user5,
                null, null, null);
        ticketRepository.save(ticket6);

        Ticket ticket7 = new Ticket(null, "Scratched eye", "I found a chicken with a " +
                "scratched eye on my balcony.", TicketStatus.OPEN, animal12, user7, user7, null, null);
        ticketRepository.save(ticket7);

        Ticket ticket8 = new Ticket(null, "Bald spots", "I was walking through the park with " +
                "my grandson, and we noticed that one of the horses had some bald spots, did you know that?",
                TicketStatus.OPEN, animal15, user8, user7, null, null);
        ticketRepository.save(ticket8);

        List<TicketStatus> statuses = new ArrayList<>();
        statuses.add(TicketStatus.OPEN);
        statuses.add(TicketStatus.IN_PROGRESS);

        for (Animal animal : animalsToSeed) {
            animal.setTicketAmount(ticketRepository.countByAnimalIdAndStatusIn(animal.getId(), statuses));
            animalRepository.save(animal);
        }

//
    }
}
