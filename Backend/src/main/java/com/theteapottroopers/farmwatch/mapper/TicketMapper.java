package com.theteapottroopers.farmwatch.mapper;

import com.theteapottroopers.farmwatch.dto.TicketDtoAll;
import com.theteapottroopers.farmwatch.dto.TicketDtoNew;
import com.theteapottroopers.farmwatch.dto.TicketDtoUpdate;
import com.theteapottroopers.farmwatch.model.ticket.Ticket;
import com.theteapottroopers.farmwatch.model.ticket.TicketMessage;
import com.theteapottroopers.farmwatch.model.ticket.TicketStatus;
import com.theteapottroopers.farmwatch.service.AnimalService;
import com.theteapottroopers.farmwatch.service.UserService;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Does DTO <-> Model conversion for Ticket
 */
public class TicketMapper extends Mapper{

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
                .summary(emptyToNull(ticket.getSummary()))
                .description(emptyToNull(ticket.getDescription()))
                .status(ticket.getStatus().toString())
                .reportDateTime(ticket.getReportDateTime())
                .animal((ticket.getAnimal() != null) ?
                        getAnimalFromTicket(ticket) : null)
                .reportedByUser(getReportedByUserFromTicket(ticket))
                .assignedToUser((ticket.getAssignedTo() != null) ?
                        getAssignedToUserFromTicket(ticket) : null)
                .ticketMessageIds(ticketMessagesDtoIdList)
                .build();
        return ticketDtoAllBuilder;
    }

    private static TicketDtoAll.UserDtoUsername getAssignedToUserFromTicket(Ticket ticket) {
        return new TicketDtoAll.UserDtoUsername(
                ticket.getAssignedTo().getId(),
                ticket.getAssignedTo().getUsername());
    }

    private static TicketDtoAll.UserDtoUsername getReportedByUserFromTicket(Ticket ticket) {
        return new TicketDtoAll.UserDtoUsername(
                ticket.getReportedBy().getId(),
                ticket.getReportedBy().getUsername());
    }

    private static TicketDtoAll.AnimalDtoAnimalName getAnimalFromTicket(Ticket ticket) {
        return new TicketDtoAll.AnimalDtoAnimalName(
                ticket.getAnimal().getId(),
                ticket.getAnimal().getName());
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
                .summary(ticketDtoNew.getSummary())
                .description(ticketDtoNew.getDescription())
                .status(TicketStatus.OPEN)
                .animal((ticketDtoNew.getAnimalId() != null) ?
                        animalService.findAnimalById(ticketDtoNew.getAnimalId()) : null)
                .reportedBy(userService.findUserByUsername(ticketDtoNew.getReportUsername()))
                .assignedTo(null)
                .build();
        return ticketAllBuilder;
    }

    public TicketDtoUpdate toTicketDtoUpdate(Ticket ticket){
        TicketDtoUpdate ticketDtoUpdate = new TicketDtoUpdate();
        ticketDtoUpdate.setId(ticket.getId());
        if (ticket.getAnimal() == null){
            ticketDtoUpdate.setAnimalId(null);
            ticketDtoUpdate.setAnimalName(null);
        } else {
            ticketDtoUpdate.setAnimalId(ticket.getAnimal().getId());
            ticketDtoUpdate.setAnimalName(ticket.getAnimal().getName());
        }
        ticketDtoUpdate.setSummary(ticket.getSummary());
        ticketDtoUpdate.setDescription(ticket.getDescription());
        ticketDtoUpdate.setStatus(ticket.getStatus());
        if(ticketDtoUpdate.getAssignedTo() == null){
            ticketDtoUpdate.setAssignedTo(null);
            ticketDtoUpdate.setAssignedToName(null);
        }else{
            ticketDtoUpdate.setAssignedTo(ticket.getAssignedTo().getId());
            ticketDtoUpdate.setAssignedToName(getFullName(ticket).toString());
        }
        return ticketDtoUpdate;
    }

    private static StringBuilder getFullName(Ticket ticket) {
        StringBuilder stringBuilder = new StringBuilder(ticket.getAssignedTo().getFirstname());
        stringBuilder.append(" ");
        stringBuilder.append(ticket.getAssignedTo().getLastname());
        return stringBuilder;
    }
}