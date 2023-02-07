package com.theteapottroopers.farmwatch.resource;

import com.theteapottroopers.farmwatch.dto.TicketMessageDtoAll;
import com.theteapottroopers.farmwatch.mapper.TicketMessageMapper;
import com.theteapottroopers.farmwatch.service.TicketMessageService;
import com.theteapottroopers.farmwatch.service.TicketService;
import com.theteapottroopers.farmwatch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public TicketMessageResource(TicketService ticketService, TicketMessageService ticketMessageService, UserService userService) {
        this.ticketMessageService = ticketMessageService;
        this.ticketMessageMapper = new TicketMessageMapper(userService, ticketService);
    }

    @PostMapping
    public ResponseEntity<?> addTicketMessage(@RequestBody TicketMessageDtoAll ticketMessageDtoAll){
        ticketMessageService.addTicketMessage(ticketMessageMapper.toTicketMessage(ticketMessageDtoAll));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
