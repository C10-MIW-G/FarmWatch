package com.theteapottroopers.farmwatch.mapper;

import com.theteapottroopers.farmwatch.dto.TicketMessageDtoAll;
import com.theteapottroopers.farmwatch.model.ticket.TicketMessage;
import com.theteapottroopers.farmwatch.service.TicketService;
import com.theteapottroopers.farmwatch.service.UserService;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Does DTO <-> Model conversion for TicketMessage
 */
public class TicketMessageMapper extends Mapper {

    private final UserService userService;
    private final TicketService ticketService;

    public TicketMessageMapper(UserService userService, TicketService ticketService) {
        this.userService = userService;
        this.ticketService = ticketService;
    }

    public TicketMessage toTicketMessage(TicketMessageDtoAll ticketMessageDtoAll) {
        TicketMessage ticketMessageBuilder = TicketMessage.builder()
                .sendBy(userService.findUserById(ticketMessageDtoAll.getSendByUser().getId()))
                .message(emptyToNull(ticketMessageDtoAll.getMessage()))
                .ticket((ticketMessageDtoAll.getTicketId() != null ?
                        ticketService.findTicketById(ticketMessageDtoAll.getTicketId()) : null))
                .privateMessage(ticketMessageDtoAll.isPrivateMessage())
                .build();
        return ticketMessageBuilder;
    }

    public TicketMessageDtoAll toTicketMessageDtoAll(TicketMessage ticketMessage){
        TicketMessageDtoAll ticketMessageDtoAll = TicketMessageDtoAll.builder()
                .id(ticketMessage.getId())
                .sendByUser(new TicketMessageDtoAll.UserDtoUsername(
                        ticketMessage.getSendBy().getId(),
                        ticketMessage.getSendBy().getUsername(),
                        ticketMessage.getSendBy().getRole().toString().substring(5).toLowerCase()))
                .messageLocalDateTime(ticketMessage.getMessageDateTime())
                .message(ticketMessage.getMessage())
                .ticketId(ticketMessage.getTicket().getId())
                .privateMessage(ticketMessage.isPrivateMessage())
                .build();
        return ticketMessageDtoAll;
    }
}
