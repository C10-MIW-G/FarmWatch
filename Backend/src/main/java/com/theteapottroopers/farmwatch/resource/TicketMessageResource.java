package com.theteapottroopers.farmwatch.resource;

import com.theteapottroopers.farmwatch.dto.TicketDtoAll;
import com.theteapottroopers.farmwatch.dto.TicketMessageDtoAll;
import com.theteapottroopers.farmwatch.mapper.TicketMessageMapper;
import com.theteapottroopers.farmwatch.model.Ticket;
import com.theteapottroopers.farmwatch.model.TicketMessage;
import com.theteapottroopers.farmwatch.service.TicketMessageService;
import com.theteapottroopers.farmwatch.service.TicketService;
import com.theteapottroopers.farmwatch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    public TicketMessageResource(TicketService ticketService,
                                 TicketMessageService ticketMessageService,
                                 UserService userService) {
        this.ticketMessageService = ticketMessageService;
        this.ticketMessageMapper = new TicketMessageMapper(userService, ticketService);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('CARETAKER', 'ADMIN')")
    public ResponseEntity<TicketMessageDtoAll> getTicketMessageById(@PathVariable("id") Long id){
        TicketMessage ticketMessage = ticketMessageService.findTicketMessageById(id);
        TicketMessageDtoAll ticketMessageDtoAll = ticketMessageMapper.toTicketMessageDtoAll(ticketMessage);
        return new ResponseEntity<>(ticketMessageDtoAll, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('CARETAKER', 'ADMIN')")
    public ResponseEntity<?> addTicketMessage(@RequestBody TicketMessageDtoAll ticketMessageDtoAll){
        TicketMessage ticketMessage = ticketMessageMapper.toTicketMessage(ticketMessageDtoAll);
        ticketMessageService.addTicketMessage(ticketMessage);
        ticketMessageDtoAll = ticketMessageMapper.toTicketMessageDtoAll(ticketMessage);
        return new ResponseEntity<>(ticketMessageDtoAll,HttpStatus.CREATED);
    }
}