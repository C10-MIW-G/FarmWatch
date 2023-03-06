package com.theteapottroopers.farmwatch.resource;

import com.theteapottroopers.farmwatch.dto.TicketMessageDtoAll;
import com.theteapottroopers.farmwatch.mapper.TicketMessageMapper;
import com.theteapottroopers.farmwatch.model.ticket.Ticket;
import com.theteapottroopers.farmwatch.model.ticket.TicketMessage;
import com.theteapottroopers.farmwatch.security.user.Role;
import com.theteapottroopers.farmwatch.security.user.User;
import com.theteapottroopers.farmwatch.service.TicketMessageService;
import com.theteapottroopers.farmwatch.service.TicketService;
import com.theteapottroopers.farmwatch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Handles TicketMessage requests
 */
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/ticket/message")
public class TicketMessageResource {

    private final TicketMessageService ticketMessageService;
    private final TicketMessageMapper ticketMessageMapper;
    private final TicketService ticketService;

    public TicketMessageResource(TicketService ticketService,
                                 TicketMessageService ticketMessageService,
                                 UserService userService, TicketService ticketService1) {
        this.ticketMessageService = ticketMessageService;
        this.ticketService = ticketService1;
        this.ticketMessageMapper = new TicketMessageMapper(userService, ticketService);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'CARETAKER', 'ADMIN')")
    public ResponseEntity<TicketMessageDtoAll> getTicketMessageById(@PathVariable("id") Long id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof User){
            TicketMessage ticketMessage = ticketMessageService.findTicketMessageById(id);
            if(ticketMessage.getTicket().isTicketFromUserId(((User) principal).getId()) ||
                    ((User) principal).getRole() != Role.ROLE_USER) {
                TicketMessageDtoAll ticketMessageDtoAll = ticketMessageMapper.toTicketMessageDtoAll(ticketMessage);
                return new ResponseEntity<>(ticketMessageDtoAll, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'CARETAKER', 'ADMIN')")
    public ResponseEntity<?> addTicketMessage(@RequestBody TicketMessageDtoAll ticketMessageDtoAll){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof User) {
            Ticket ticket = ticketService.findTicketById(ticketMessageDtoAll.getTicketId());
            if (ticket.isTicketFromUserId(((User) principal).getId()) ||
                    ((User) principal).getRole() != Role.ROLE_USER) {
                TicketMessage ticketMessage = ticketMessageMapper.toTicketMessage(ticketMessageDtoAll);
                ticketMessageService.addTicketMessage(ticketMessage);
                ticketMessageDtoAll = ticketMessageMapper.toTicketMessageDtoAll(ticketMessage);
                return new ResponseEntity<>(ticketMessageDtoAll, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}