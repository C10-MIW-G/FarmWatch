package com.theteapottroopers.farmwatch.resource;

import com.theteapottroopers.farmwatch.service.SeederService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Eelke Mulder
 *
 * This is the resource for the seeder.
 */

@CrossOrigin (origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("")
public class SeederResource {
    private final SeederService seederService;

    public SeederResource(SeederService seederService) {
        this.seederService = seederService;
    }

    @PostMapping("/seed")
    public ResponseEntity<?> seedAnimals() throws IOException {
        seederService.runSeeder();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
