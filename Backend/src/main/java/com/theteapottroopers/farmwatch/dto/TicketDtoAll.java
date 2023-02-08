package com.theteapottroopers.farmwatch.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Ticket DTO containing all fields
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TicketDtoAll {

    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime reportDateTime;
    private Long animalId;
    private Long reportedByUserId;
    private Long assignedToUserId;
    private List<TicketMessageDtoAll> ticketMessages;
}
