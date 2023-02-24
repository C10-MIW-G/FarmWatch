package com.theteapottroopers.farmwatch.service;

import com.theteapottroopers.farmwatch.model.ticket.TicketMessage;
import com.theteapottroopers.farmwatch.repository.TicketMessageRepository;
import com.theteapottroopers.farmwatch.validation.TicketValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * The service for the TicketMessage requests
 */
@Service
public class TicketMessageService{

    private final TicketMessageRepository ticketMessageRepository;
    private final TicketValidation ticketValidation;

    @Autowired
    public TicketMessageService(TicketMessageRepository ticketMessageRepository, TicketValidation ticketValidation) {
        this.ticketMessageRepository = ticketMessageRepository;
        this.ticketValidation = ticketValidation;
    }

    public void addTicketMessage(TicketMessage ticketMessage) {
        ticketValidation.instanceMessageCheck(ticketMessage);
        ticketMessageRepository.save(ticketMessage);
    }

    public TicketMessage findTicketMessageById(Long id){
        return ticketMessageRepository.findById(id).orElseThrow();
    }
}
