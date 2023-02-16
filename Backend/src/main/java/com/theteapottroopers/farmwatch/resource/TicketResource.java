package com.theteapottroopers.farmwatch.resource;

import com.theteapottroopers.farmwatch.dto.TicketDtoAll;
import com.theteapottroopers.farmwatch.dto.TicketDtoNew;
import com.theteapottroopers.farmwatch.mapper.TicketMapper;
import com.theteapottroopers.farmwatch.mapper.TicketMessageMapper;
import com.theteapottroopers.farmwatch.model.Ticket;
import com.theteapottroopers.farmwatch.repository.TicketMessageRepository;
import com.theteapottroopers.farmwatch.service.AnimalService;
import com.theteapottroopers.farmwatch.service.TicketMessageService;
import com.theteapottroopers.farmwatch.service.TicketService;
import com.theteapottroopers.farmwatch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Handles Ticket requests
 */
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/ticket")
public class TicketResource {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    public TicketResource(TicketService ticketService, UserService userService,
                          AnimalService animalService) {
        this.ticketService = ticketService;
        this.ticketMapper = new TicketMapper(userService, animalService);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('CARETAKER', 'ADMIN')")
    public ResponseEntity<List<TicketDtoAll>> getAllTickets(){
        List<Ticket> allTickets = ticketService.findAllTickets();
        List<TicketDtoAll> allTicketDtos = new ArrayList<>();
        for (Ticket ticket: allTickets) {
            allTicketDtos.add(ticketMapper.toTicketDtoAll(ticket));
        }
        return new ResponseEntity<>(allTicketDtos, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'CARETAKER', 'ADMIN')")
    public ResponseEntity<?> addTicket(@RequestBody TicketDtoNew ticketDtoNew){
        ticketService.addTicket(ticketMapper.toTicketFromNew(ticketDtoNew));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('CARETAKER', 'ADMIN')")
    public ResponseEntity<TicketDtoAll> getTicketById(@PathVariable("id") Long id){
        Ticket ticket = ticketService.findTicketById(id);
        TicketDtoAll ticketDtoAll = ticketMapper.toTicketDtoAll(ticket);
        return new ResponseEntity<>(ticketDtoAll, HttpStatus.OK);
    }
}