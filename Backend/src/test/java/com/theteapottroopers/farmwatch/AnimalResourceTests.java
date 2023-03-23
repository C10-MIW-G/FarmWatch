package com.theteapottroopers.farmwatch;

import com.theteapottroopers.farmwatch.dto.AnimalDetailDto;
import com.theteapottroopers.farmwatch.dto.AnimalOverviewDto;
import com.theteapottroopers.farmwatch.mapper.AnimalMapper;
import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.resource.AnimalResource;
import com.theteapottroopers.farmwatch.service.AnimalService;
import com.theteapottroopers.farmwatch.service.FileStorageService;
import com.theteapottroopers.farmwatch.service.TicketService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @Author is Berend Boksma <b.boksma@st.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */


@SpringBootTest
public class AnimalResourceTests {

    @InjectMocks
    private AnimalResource animalResource;

    @Mock
    private AnimalService animalService;
    @Mock
    private TicketService ticketService;
    @Mock
    private AnimalMapper animalMapper;
    @Mock
    private FileStorageService fileStorageService;

    @Test
    @DisplayName("Test controller for correct response")
    void testControllerResponse(){
        List<AnimalOverviewDto>  animalOverviewDtos = new ArrayList<>();

        ResponseEntity<List<AnimalOverviewDto>> expectedResponse = new ResponseEntity<>(animalOverviewDtos, HttpStatus.OK);
        ResponseEntity<List<AnimalOverviewDto>> actualResponse = animalResource.getAllAnimals();

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Test if add animal gives correct response")
    void testAddAnimalResponse(){
        ResponseEntity<?> expectedResponse = new ResponseEntity<>(HttpStatus.CREATED);
        ResponseEntity<?> actualResponse = animalResource.addAnimal(new AnimalDetailDto());

        assertEquals(expectedResponse, actualResponse);
    }
}
