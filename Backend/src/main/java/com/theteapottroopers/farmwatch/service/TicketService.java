package com.theteapottroopers.farmwatch.service;

import com.theteapottroopers.farmwatch.exception.AnimalNotFoundException;
import com.theteapottroopers.farmwatch.exception.TicketNotFoundException;
import com.theteapottroopers.farmwatch.exception.UserNotFoundException;
import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.model.Ticket;
import com.theteapottroopers.farmwatch.repository.TicketRepository;

import com.theteapottroopers.farmwatch.security.user.User;
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

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> findAllTickets(){
        return ticketRepository.findAll();
    }

    public Ticket findTicketById(Long id){
        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(
                "Ticket by id " + id + " was not found!"));
    }

    public void addTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }
}
