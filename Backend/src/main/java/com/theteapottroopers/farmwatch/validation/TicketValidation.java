package com.theteapottroopers.farmwatch.validation;

import com.theteapottroopers.farmwatch.exception.FieldHasNoInputException;
import com.theteapottroopers.farmwatch.model.ticket.Ticket;
import com.theteapottroopers.farmwatch.model.ticket.TicketMessage;
import com.theteapottroopers.farmwatch.repository.TicketMessageRepository;
import com.theteapottroopers.farmwatch.repository.TicketRepository;
import com.theteapottroopers.farmwatch.security.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * @Author: M.S. Pilat <pilat_m@msn.com>
 * <p>
 * dit programma doet iets
 */

@Service
public class TicketValidation {

    TicketRepository ticketRepository;
    TicketMessageRepository ticketMessageRepository;

    @Autowired
    public TicketValidation(TicketRepository ticketRepository, TicketMessageRepository ticketMessageRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketMessageRepository = ticketMessageRepository;
    }

    public void instanceMessageCheck(TicketMessage ticketMessage) {
        userIdMustNotBeNull(ticketMessage);
        dateTimeMustNotBeNull(ticketMessage);
        messageMustNotBeNull(ticketMessage);
        ticketIdMustNotBeNull(ticketMessage);
        messageMustContainACharacter(ticketMessage);
    }
    private void userIdMustNotBeNull(TicketMessage ticketMessage) {
        if (ticketMessage.getSendBy().getId() == null){
            throw new FieldHasNoInputException("Ticket message has to be assigned to user");
        }
    }
    private void dateTimeMustNotBeNull(TicketMessage ticketMessage) {
        if (ticketMessage.getMessageDateTime() == null){
            throw new DateTimeException("Message should have a date and time assigned to it");
        }
    }

    private void messageMustNotBeNull(TicketMessage ticketMessage) {
        if (ticketMessage.getMessage() == null){
            throw new FieldHasNoInputException("You have to fill in a message");
        }
    }

    private void messageMustContainACharacter(TicketMessage ticketMessage) {
        String pattern = "[\\x21-\\x7E]+";
        if (!ticketMessage.getMessage().substring(0, 1).matches(pattern)){
            throw new FieldHasNoInputException("Message has to start with a word");
        }
    }

    private void ticketIdMustNotBeNull(TicketMessage ticketMessage) {
        if (ticketMessage.getTicket() == null){
            throw new FieldHasNoInputException("Message should always be about a ticket");
        }
    }
}
