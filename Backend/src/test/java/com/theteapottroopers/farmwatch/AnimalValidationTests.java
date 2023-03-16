package com.theteapottroopers.farmwatch;

import com.theteapottroopers.farmwatch.exception.InputIsToLargeException;
import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.validation.AnimalValidation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DateTimeException;
import java.time.LocalDate;

import static com.theteapottroopers.farmwatch.validation.AnimalValidation.MAX_LENGTH_DESCRIPTION;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AnimalValidationTests {
    @Autowired
    AnimalValidation animalValidation;

    @Test
    public void animalDateMustBePresentOrPast() {

        LocalDate dateInFuture = LocalDate.now().plusDays(1);
        Animal animalToTest = new Animal("Clara", "Chicken", "Galus galus domesticus",
                "asdf", dateInFuture, null);

        assertThrows(DateTimeException.class, () -> {
            animalValidation.dateMustBePresentOrPast(animalToTest);
        });

    }

    @Test
    public void animalDescriptionToLong() {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < MAX_LENGTH_DESCRIPTION + 1; i++) {
            stringBuilder.append("a");
        }
        String description = stringBuilder.toString();

        Animal animalToTest = new Animal("Clara", "Chicken", "Galus galus domesticus",
                description, LocalDate.now(), null);

        assertThrows(InputIsToLargeException.class, () -> {
            animalValidation.descriptionToLong(animalToTest);
        });
    }

}
