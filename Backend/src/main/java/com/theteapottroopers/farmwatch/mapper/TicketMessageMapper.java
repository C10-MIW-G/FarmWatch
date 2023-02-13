package com.theteapottroopers.farmwatch.mapper;

import com.theteapottroopers.farmwatch.dto.TicketMessageDtoAll;
import com.theteapottroopers.farmwatch.dto.TicketMessageDtoId;
import com.theteapottroopers.farmwatch.model.TicketMessage;
import com.theteapottroopers.farmwatch.service.TicketService;
import com.theteapottroopers.farmwatch.service.UserService;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Does DTO <-> Model conversion for TicketMessage
 */
public class TicketMessageMapper {

    private final UserService userService;
    private final TicketService ticketService;

    public TicketMessageMapper(UserService userService, TicketService ticketService) {
        this.userService = userService;
        this.ticketService = ticketService;
    }

    public TicketMessage toTicketMessage(TicketMessageDtoAll ticketMessageDtoAll) {
        TicketMessage ticketMessageBuilder = TicketMessage.builder()
                .sendBy(userService.findUserById(ticketMessageDtoAll.getSendByUserId()))
                .message(ticketMessageDtoAll.getMessage())
                .ticket((ticketMessageDtoAll.getTicketId() != null ?
                        ticketService.findTicketById(ticketMessageDtoAll.getTicketId()) : null))
                .build();
        return ticketMessageBuilder;
    }
}
