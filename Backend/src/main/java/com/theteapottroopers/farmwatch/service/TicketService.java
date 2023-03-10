package com.theteapottroopers.farmwatch.service;

import com.theteapottroopers.farmwatch.dto.TicketDtoUpdate;
import com.theteapottroopers.farmwatch.exception.TicketNotFoundException;
import com.theteapottroopers.farmwatch.model.ticket.Ticket;
import com.theteapottroopers.farmwatch.repository.TicketRepository;
import com.theteapottroopers.farmwatch.security.user.User;
import com.theteapottroopers.farmwatch.validation.TicketValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * The service for the Ticket requests
 */
@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketValidation ticketValidation;
    private final UserService userService;
    private final AnimalService animalService;
    @Autowired
    public TicketService(TicketRepository ticketRepository, UserService userService, AnimalService animalService,
                         TicketValidation ticketValidation) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.animalService = animalService;
        this.ticketValidation = ticketValidation;
    }


    public List<Ticket> findAllTickets(){
        if (ticketRepository.findAll().size() == 0) {
            throw new TicketNotFoundException("No tickets were found!");
        }
        return ticketRepository.findAll();
    }

    public Ticket findTicketById(Long id){
        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(
                "Ticket by id " + id + " was not found!"));
    }

    public void addTicket(Ticket ticket) {
        ticketValidation.instanceTicketCheck(ticket);
        ticketRepository.save(ticket);
    }

    public void deleteTicketByAnimalId(Long id){
        List<Ticket> allTickets = ticketRepository.findAll();
        for (Ticket ticket : allTickets) {
            if (ticket.getAnimal() != null) {
                if(ticket.getAnimal().getId() == id){
                    ticketRepository.deleteById(ticket.getId());
                }
            }
        }
    }

    public void updateTicket(TicketDtoUpdate ticketDtoUpdate){
        Ticket ticketToUpdate = ticketRepository.findById(ticketDtoUpdate.getId()).get();
        updateAnimalInTicket(ticketDtoUpdate, ticketToUpdate);
        ticketToUpdate.setSummary(ticketDtoUpdate.getSummary());
        ticketToUpdate.setDescription(ticketDtoUpdate.getDescription());
        ticketToUpdate.setStatus(ticketDtoUpdate.getStatus());
        updateUserInTicket(ticketDtoUpdate, ticketToUpdate);
        ticketRepository.save(ticketToUpdate);
    }

    private void updateUserInTicket(TicketDtoUpdate ticketDtoUpdate, Ticket ticketToUpdate) {
        if(ticketDtoUpdate.getAssignedTo() == null && ticketDtoUpdate.getAssignedToName() == null) {
            ticketToUpdate.setAssignedTo(null);
         } else if (ticketDtoUpdate.getAssignedToName() != null &&
               ticketDtoUpdate.getAssignedTo() == null) {
            ticketToUpdate.setAssignedTo(userService.findUserByUsername(ticketDtoUpdate.getAssignedToName()));
        } else {
            ticketToUpdate.setAssignedTo(userService.findUserById(ticketDtoUpdate.getAssignedTo()));
        }
    }

    private void updateAnimalInTicket(TicketDtoUpdate ticketDtoUpdate, Ticket ticketToUpdate) {
        if(ticketDtoUpdate.getAnimalId() == null) {
            ticketToUpdate.setAnimal(null);
        } else {
            ticketToUpdate.setAnimal(animalService.findAnimalById(ticketDtoUpdate.getAnimalId()));
        }
    }
}
