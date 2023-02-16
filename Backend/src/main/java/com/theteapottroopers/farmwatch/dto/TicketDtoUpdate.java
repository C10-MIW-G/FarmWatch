package com.theteapottroopers.farmwatch.dto;

import com.theteapottroopers.farmwatch.model.ticket.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: M.S. Pilat <pilat_m@msn.com>
 * <p>
 * dit programma doet iets
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketDtoUpdate {

    private Long id;
    private String subject;
    private String description;
    private TicketStatus ticketStatus;
    private Long assignedTo;

}
