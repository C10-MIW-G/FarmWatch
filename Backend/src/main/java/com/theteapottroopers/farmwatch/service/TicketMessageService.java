package com.theteapottroopers.farmwatch.service;

import com.theteapottroopers.farmwatch.model.Ticket;
import com.theteapottroopers.farmwatch.model.TicketMessage;
import com.theteapottroopers.farmwatch.repository.TicketMessageRepository;
import com.theteapottroopers.farmwatch.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * The service for the TicketMessage requests
 */
@Service
public class TicketMessageService{

    private final TicketMessageRepository ticketMessageRepository;

    @Autowired
    public TicketMessageService(TicketMessageRepository ticketMessageRepository,
                                TicketRepository ticketRepository) {
        this.ticketMessageRepository = ticketMessageRepository;
    }

    public void addTicketMessage(TicketMessage ticketMessage) {
        ticketMessageRepository.save(ticketMessage);
    }
}
