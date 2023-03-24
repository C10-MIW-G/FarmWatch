package com.theteapottroopers.farmwatch.service;

import com.theteapottroopers.farmwatch.repository.*;
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
    private final TicketMessageRepository ticketMessageRepository;
    private final FileStorageService fileStorageService;

    @Autowired
    public SeederService(AnimalRepository animalRepository,
                         TicketRepository ticketRepository,
                         UserRepository userRepository,
                         PasswordEncoder passwordEncoder, StorageRepository storageRepository, FileStorageService fileStorageService, TicketMessageRepository ticketMessageRepository) {
        this.animalRepository = animalRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.storageRepository = storageRepository;
        this.ticketMessageRepository = ticketMessageRepository;
        this.fileStorageService = fileStorageService;
    }

    public void runSeeder() throws IOException {
        Seeder animalSeeder = new Seeder(
                animalRepository,
                ticketRepository,
                userRepository,
                passwordEncoder,
                storageRepository,
                ticketMessageRepository,
                fileStorageService);
        animalSeeder.SeedAnimals();
    }
}
