package com.theteapottroopers.farmwatch.dto;

import com.theteapottroopers.farmwatch.model.ticket.TicketStatus;
import lombok.*;

/**
 * @Author: M.S. Pilat <pilat_m@msn.com>
 * <p>
 * dit programma doet iets
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TicketDtoUpdate {

    private Long id;
    private Long animalId;
    private String animalName;
    private String summary;
    private String description;
    private TicketStatus status;
    private Long assignedTo;
    private String assignedToName;
    private String imageFileName;

}
