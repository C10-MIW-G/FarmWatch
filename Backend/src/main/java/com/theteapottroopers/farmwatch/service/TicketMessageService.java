package com.theteapottroopers.farmwatch.service;

import com.theteapottroopers.farmwatch.model.ticket.TicketMessage;
import com.theteapottroopers.farmwatch.repository.TicketMessageRepository;
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

    @Autowired
    public TicketMessageService(TicketMessageRepository ticketMessageRepository) {
        this.ticketMessageRepository = ticketMessageRepository;
    }

    public void addTicketMessage(TicketMessage ticketMessage) {
        ticketMessageRepository.save(ticketMessage);
    }

    public TicketMessage findTicketMessageById(Long id){
        return ticketMessageRepository.findById(id).orElseThrow();
    }
}
