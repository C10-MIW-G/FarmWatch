package com.theteapottroopers.farmwatch.validation;

import com.theteapottroopers.farmwatch.exception.FieldHasNoInputException;
import com.theteapottroopers.farmwatch.exception.InputIsToLargeException;
import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * @Author: M.S. Pilat <pilat_m@msn.com>
 * <p>
 * this class validates date for Animal
 */

@Service
public class AnimalValidation {
    public static final int MAX_LENGTH_DESCRIPTION = 1000;
    private final UserRepository userRepository;

    @Autowired
    public AnimalValidation(UserRepository userRepository) {this.userRepository = userRepository;}

    public void instanceCheck(Animal animal) {
        nameMustNotBeNull(animal);
        dateMustNotBeNull(animal);
        dateMustBePresentOrPast(animal);
        descriptionToLong(animal);
    }

    private void nameMustNotBeNull(Animal animal) {
        if (animal.getName() == null){
            throw new FieldHasNoInputException("You have to fill in the animals name");
        }
    }

    private void dateMustNotBeNull(Animal animal) {
        if (animal.getDateOfBirth() == null) {
            throw new DateTimeException("Please pick a date");
        }
    }

    private void dateMustBePresentOrPast(Animal animal) {
        if (animal.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new DateTimeException("The date should be today or in the past");
        }
    }

    private void descriptionToLong(Animal animal) {
        if (animal.getDescription() != null){
            if (animal.getDescription().length() > MAX_LENGTH_DESCRIPTION) {
                throw new InputIsToLargeException("Your input is to long");
            }
        }
    }

}
