package com.theteapottroopers.farmwatch.mapper;

import com.theteapottroopers.farmwatch.dto.TicketDtoAll;
import com.theteapottroopers.farmwatch.model.Ticket;
import com.theteapottroopers.farmwatch.repository.UserRepository;
import com.theteapottroopers.farmwatch.service.AnimalService;
import com.theteapottroopers.farmwatch.service.UserService;
import org.apache.catalina.User;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Wat doet deze klasse?
 */
public class TicketMapper {

    private final UserService userService;
    private final AnimalService animalService;

    public TicketMapper(UserService userService, AnimalService animalService) {
        this.userService = userService;
        this.animalService = animalService;
    }

    public TicketDtoAll toTicketDtoAll(Ticket ticket){
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
                .build();
        return ticketDtoAllBuilder;
    }

    public Ticket toTicket(TicketDtoAll ticketDtoAll) {
        Ticket ticketAllBuilder = Ticket.builder()
                .title(ticketDtoAll.getTitle())
                .description(ticketDtoAll.getDescription())
                .status(ticketDtoAll.getStatus())
                .animal((ticketDtoAll.getAnimalId() != null) ?
                        animalService.findAnimalById(ticketDtoAll.getAnimalId()) : null)
                .reportedBy(userService.findUserById(ticketDtoAll.getReportedByUserId()))
                .assignedTo((ticketDtoAll.getAssignedToUserId() != null ?
                        userService.findUserById(ticketDtoAll.getAssignedToUserId()) : null))
                .build();
        return ticketAllBuilder;
    }
}
