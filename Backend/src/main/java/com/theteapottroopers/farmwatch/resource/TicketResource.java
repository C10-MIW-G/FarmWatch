package com.theteapottroopers.farmwatch.resource;

import com.theteapottroopers.farmwatch.dto.TicketDtoAll;
import com.theteapottroopers.farmwatch.dto.TicketDtoNew;
import com.theteapottroopers.farmwatch.dto.TicketDtoUpdate;
import com.theteapottroopers.farmwatch.exception.response.ErrorResponse;
import com.theteapottroopers.farmwatch.mapper.TicketMapper;
import com.theteapottroopers.farmwatch.model.ticket.Ticket;
import com.theteapottroopers.farmwatch.model.ticket.TicketStatus;
import com.theteapottroopers.farmwatch.security.user.Role;
import com.theteapottroopers.farmwatch.security.user.User;
import com.theteapottroopers.farmwatch.service.AnimalService;
import com.theteapottroopers.farmwatch.service.FileStorageService;
import com.theteapottroopers.farmwatch.service.TicketService;
import com.theteapottroopers.farmwatch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
    private final AnimalService animalService;

    public TicketResource(TicketService ticketService, UserService userService,
                          AnimalService animalService, FileStorageService fileStorageService, AnimalService animalService1) {
        this.ticketService = ticketService;
        this.animalService = animalService1;
        this.ticketMapper = new TicketMapper(userService, animalService, fileStorageService);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'CARETAKER', 'ADMIN')")
    public ResponseEntity<List<TicketDtoAll>> getAllTickets(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Ticket> allTickets = ticketService.findAllTickets();
        List<TicketDtoAll> allTicketDtos = new ArrayList<>();
        if(principal instanceof User){
            for (Ticket ticket: allTickets) {
                if (ticket.isTicketFromUserId(((User) principal).getId()) ||
                        ((User) principal).getRole() != Role.ROLE_USER) {
                    allTicketDtos.add(ticketMapper.toTicketDtoAll(ticket));
                }
            }
        }
        return new ResponseEntity<>(allTicketDtos, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'CARETAKER', 'ADMIN')")
    public ResponseEntity<?> addTicket(@RequestBody TicketDtoNew ticketDtoNew){
        ticketService.addTicket(ticketMapper.toTicketFromNew(ticketDtoNew));
        if(ticketDtoNew.getAnimalId() != null) {
            animalService.setTicketAmountForAnimal(ticketDtoNew.getAnimalId());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','CARETAKER', 'ADMIN')")
    public ResponseEntity<?> getTicketById(@PathVariable("id") Long id){
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Ticket ticket = ticketService.findTicketById(id);
            if(principal instanceof User) {
                if (ticket.isTicketFromUserId(((User) principal).getId()) ||
                        ((User) principal).getRole() != Role.ROLE_USER) {
                    TicketDtoAll ticketDtoAll = ticketMapper.toTicketDtoAll(ticket);
                    return new ResponseEntity<>(ticketDtoAll, HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception exception) {
            return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage()));
        }
    }

    @GetMapping("/status")
    @PreAuthorize("hasAnyRole('CARETAKER', 'ADMIN')")
    public ResponseEntity<List<TicketStatus>> getAllTicketStatus(){
        List<TicketStatus> allTicketStatus = Arrays.asList(TicketStatus.values());
        return new ResponseEntity<>(allTicketStatus, HttpStatus.OK);
    }

    @GetMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('CARETAKER', 'ADMIN')")
    public ResponseEntity<TicketDtoUpdate> getLeanTicketById(@PathVariable("id") Long id) {
        Ticket ticket = ticketService.findTicketById(id);
        TicketDtoUpdate ticketDtoUpdate = ticketMapper.toTicketDtoUpdate(ticket);
        return new ResponseEntity<>(ticketDtoUpdate, HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('CARETAKER', 'ADMIN')")
    public ResponseEntity<HttpStatus>updateTicket(@RequestBody TicketDtoUpdate ticketDtoUpdate) {
        ticketService.updateTicket(ticketDtoUpdate);
        if(ticketDtoUpdate.getAnimalId() != null) {
            animalService.setTicketAmountForAnimal(ticketDtoUpdate.getAnimalId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}