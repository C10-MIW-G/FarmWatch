package com.theteapottroopers.farmwatch.mapper;

import com.theteapottroopers.farmwatch.dto.TicketDtoAll;
import com.theteapottroopers.farmwatch.dto.TicketDtoNew;
import com.theteapottroopers.farmwatch.dto.TicketMessageDtoId;
import com.theteapottroopers.farmwatch.model.Ticket;
import com.theteapottroopers.farmwatch.model.TicketMessage;
import com.theteapottroopers.farmwatch.service.AnimalService;
import com.theteapottroopers.farmwatch.service.UserService;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Does DTO <-> Model conversion for Ticket
 */
public class TicketMapper {

    private final UserService userService;
    private final AnimalService animalService;
    private final TicketMessageMapper ticketMessageMapper;

    public TicketMapper(UserService userService, AnimalService animalService, TicketMessageMapper ticketMessageMapper) {
        this.userService = userService;
        this.animalService = animalService;
        this.ticketMessageMapper = ticketMessageMapper;
    }

    public TicketDtoAll toTicketDtoAll(Ticket ticket){
        List<Long> ticketMessagesDtoIdList = getTicketMessagesToDtoId(ticket);
        TicketDtoAll ticketDtoAllBuilder = TicketDtoAll.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .status(ticket.getStatus())
                .reportDateTime(ticket.getReportDateTime())
                .animalId((ticket.getAnimal() != null) ?
                        ticket.getAnimal().getId() : null)
                .reportedByUserId(ticket.getReportedBy().getId())
                .assignedToUserId((ticket.getAssignedTo() != null) ?
                        ticket.getAssignedTo().getId() : null)
                .ticketMessageIds(ticketMessagesDtoIdList)
                .build();
        return ticketDtoAllBuilder;
    }

    private List<Long> getTicketMessagesToDtoId(Ticket ticket) {
        List<Long> ticketMessagesDtoIdList = new ArrayList<>();
        for(TicketMessage ticketMessage : ticket.getTicketMessages()){
            ticketMessagesDtoIdList.add(ticketMessage.getId());
        }
        return ticketMessagesDtoIdList;
    }

    public Ticket toTicketFromNew(TicketDtoNew ticketDtoNew){
        Ticket ticketAllBuilder = Ticket.builder()
                .title(ticketDtoNew.getTitle())
                .description(ticketDtoNew.getDescription())
                .status("requested")
                .animal((ticketDtoNew.getAnimalId() != null) ?
                        animalService.findAnimalById(ticketDtoNew.getAnimalId()) : null)
                .reportedBy(userService.findUserByUsername(ticketDtoNew.getReportUsername()))
                .assignedTo((null))
                .build();
        return ticketAllBuilder;
    }
}
