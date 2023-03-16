package com.theteapottroopers.farmwatch.service;

import com.theteapottroopers.farmwatch.repository.AnimalRepository;
import com.theteapottroopers.farmwatch.repository.StorageRepository;
import com.theteapottroopers.farmwatch.repository.TicketRepository;
import com.theteapottroopers.farmwatch.repository.UserRepository;
import com.theteapottroopers.farmwatch.seeds.Seeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * @author Eelke Mulder
 *
 * The service for the seeder
 */

@Service
public class SeederService {

    private final AnimalRepository animalRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StorageRepository storageRepository;

    @Autowired
    public SeederService(AnimalRepository animalRepository,
                         TicketRepository ticketRepository,
                         UserRepository userRepository,
                         PasswordEncoder passwordEncoder, StorageRepository storageRepository, FileStorageService fileStorageService) {
        this.animalRepository = animalRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.storageRepository = storageRepository;;
    }

    public void runSeeder() throws IOException {
        Seeder animalSeeder = new Seeder(
                animalRepository,
                ticketRepository,
                userRepository,
                passwordEncoder, storageRepository);
        animalSeeder.SeedAnimals();
    }
}
