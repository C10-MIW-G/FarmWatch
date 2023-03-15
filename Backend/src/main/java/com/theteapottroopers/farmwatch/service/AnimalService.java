package com.theteapottroopers.farmwatch.service;

import com.theteapottroopers.farmwatch.dto.AnimalDetailDto;
import com.theteapottroopers.farmwatch.exception.AnimalNotFoundException;
import com.theteapottroopers.farmwatch.exception.SomethingWentWrongException;
import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.model.ticket.TicketStatus;
import com.theteapottroopers.farmwatch.repository.AnimalRepository;
import com.theteapottroopers.farmwatch.repository.TicketRepository;
import com.theteapottroopers.farmwatch.validation.AnimalValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Berend  <b.boksma@st.hanze.nl>
 * <p>
 * The service for the Animals
 */

@Service
public class AnimalService {


    public static final String MESSAGE_FOR_UNKNOWN_EXCEPTION = "something went wrong while performing your request, " +
            "please contact web administrator to report a problem";

    private final AnimalRepository animalRepository;
    private final AnimalValidation animalValidation;
    private final FileStorageService fileStorageService;
    private final TicketRepository ticketRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository,
                         AnimalValidation animalValidation,
                         FileStorageService fileStorageService,
                         TicketRepository ticketRepository) {
        this.animalValidation =animalValidation;
        this.animalRepository = animalRepository;
        this.fileStorageService = fileStorageService;
        this.ticketRepository = ticketRepository;
    }

    public Animal findAnimalById(Long id){
        return animalRepository.findById(id).orElseThrow(() -> new AnimalNotFoundException(
                "Animal by id " + id + " was not found!"));
    }

    public List<Animal> findAllAnimals(){
        List<Animal> allAnimals = animalRepository.findAll();
        if (allAnimals.size() == 0){
            throw new AnimalNotFoundException("There are no animals in the database");
        }
        return allAnimals;
    }

    public void deleteAnimal(Long id){
        animalRepository.deleteById(id);
    }


    public Animal updateAnimal(AnimalDetailDto animalDetailDto){
        Animal existingAnimal = animalRepository.findById(animalDetailDto.getId()).get();
        setAnimal(animalDetailDto, existingAnimal);
        animalValidation.instanceCheck(existingAnimal);
        try {
            Animal updatedAnimal = animalRepository.save(existingAnimal);
            return updatedAnimal;
        } catch (Exception exception) {
            throw new SomethingWentWrongException(MESSAGE_FOR_UNKNOWN_EXCEPTION);
        }
    }

    public void addAnimal(Animal animal){
        animalValidation.instanceCheck(animal);
        try {
            animalRepository.save(animal);
        } catch (Exception exception) {
            throw new SomethingWentWrongException(MESSAGE_FOR_UNKNOWN_EXCEPTION);
        }
    }

    public void setTicketAmountForAnimal(Long animal_Id){
        List<TicketStatus> statuses = new ArrayList<>();
        statuses.add(TicketStatus.OPEN);
        statuses.add(TicketStatus.IN_PROGRESS);

        Optional<Animal> animal = animalRepository.findById(animal_Id);

        if(animal.isPresent()){
        Animal animalToUpdate = animal.get();
            animalToUpdate.setTicketAmount(ticketRepository.countByAnimalIdAndStatusIn(animal_Id, statuses));
            animalRepository.save(animalToUpdate);
        }
    }

    private void setAnimal(AnimalDetailDto animalDetailDto, Animal existingAnimal) {
        existingAnimal.setName(animalDetailDto.getName());
        existingAnimal.setCommonName(animalDetailDto.getCommonName());
        existingAnimal.setSpecies(animalDetailDto.getSpecies());
        existingAnimal.setDescription(animalDetailDto.getDescription());
        existingAnimal.setDateOfBirth(animalDetailDto.getDateOfBirth());
        existingAnimal.setImageData(fileStorageService.findImageDataByFileName(animalDetailDto.getImageFileName()));
    }



}
