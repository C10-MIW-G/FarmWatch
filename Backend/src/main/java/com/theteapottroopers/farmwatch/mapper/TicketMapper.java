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

    public TicketMapper(UserService userService, AnimalService animalService) {
        this.userService = userService;
        this.animalService = animalService;
    }

    public TicketDtoAll toTicketDtoAll(Ticket ticket){
        List<Long> ticketMessagesDtoIdList = getTicketMessagesId(ticket);
        TicketDtoAll ticketDtoAllBuilder = TicketDtoAll.builder()
                .id(ticket.getId())
                .subject(ticket.getSubject())
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

    private List<Long> getTicketMessagesId(Ticket ticket) {
        List<Long> ticketMessagesDtoIdList = new ArrayList<>();
        for(TicketMessage ticketMessage : ticket.getTicketMessages()){
            ticketMessagesDtoIdList.add(ticketMessage.getId());
        }
        return ticketMessagesDtoIdList;
    }

    public Ticket toTicketFromNew(TicketDtoNew ticketDtoNew){
        Ticket ticketAllBuilder = Ticket.builder()
                .subject(ticketDtoNew.getSubject())
                .description(ticketDtoNew.getDescription())
                .status("requested")
                .animal((ticketDtoNew.getAnimalId() != null) ?
                        animalService.findAnimalById(ticketDtoNew.getAnimalId()) : null)
                .reportedBy(userService.findUserByUsername(ticketDtoNew.getReportUsername()))
                .assignedTo(null)
                .build();
        return ticketAllBuilder;
    }
}
