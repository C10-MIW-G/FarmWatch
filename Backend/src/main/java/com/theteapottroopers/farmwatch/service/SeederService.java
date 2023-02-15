package com.theteapottroopers.farmwatch.service;

import com.theteapottroopers.farmwatch.repository.AnimalRepository;
import com.theteapottroopers.farmwatch.repository.TicketRepository;
import com.theteapottroopers.farmwatch.repository.UserRepository;
import com.theteapottroopers.farmwatch.seeds.Seeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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

    @Autowired
    public SeederService(AnimalRepository animalRepository,
                         TicketRepository ticketRepository,
                         UserRepository userRepository,
                         PasswordEncoder passwordEncoder) {
        this.animalRepository = animalRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void runSeeder(){
        Seeder animalSeeder = new Seeder(
                animalRepository,
                ticketRepository,
                userRepository,
                passwordEncoder);
        animalSeeder.SeedAnimals();
    }
}
