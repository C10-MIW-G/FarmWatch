package com.theteapottroopers.farmwatch.resource;

import com.theteapottroopers.farmwatch.dto.AnimalDto;
import com.theteapottroopers.farmwatch.dto.TicketDtoAll;
import com.theteapottroopers.farmwatch.mapper.TicketMapper;
import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.model.Ticket;
import com.theteapottroopers.farmwatch.service.AnimalService;
import com.theteapottroopers.farmwatch.service.TicketMessageService;
import com.theteapottroopers.farmwatch.service.TicketService;
import com.theteapottroopers.farmwatch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Wat doet deze klasse?
 */
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/ticket")
public class TicketResource {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    public TicketResource(TicketService ticketService, UserService userService,
                          AnimalService animalService, TicketMessageService ticketMessageService) {
        this.ticketService = ticketService;
        this.ticketMapper = new TicketMapper(userService, animalService, ticketMessageService);
    }

    @GetMapping
    public ResponseEntity<List<TicketDtoAll>> getAllTickets(){
        List<Ticket> allTickets = ticketService.findAllTickets();
        List<TicketDtoAll> allTicketDtos = new ArrayList<>();
        for (Ticket ticket: allTickets) {
            allTicketDtos.add(ticketMapper.toTicketDtoAll(ticket));
        }
        return new ResponseEntity<>(allTicketDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addTicket(@RequestBody TicketDtoAll ticketDtoAll){
        ticketService.addTicket(ticketMapper.toTicket(ticketDtoAll));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}