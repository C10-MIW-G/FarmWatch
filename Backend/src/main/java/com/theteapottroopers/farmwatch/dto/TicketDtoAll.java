package com.theteapottroopers.farmwatch.dto;

import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.model.TicketMessage;
import com.theteapottroopers.farmwatch.security.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Ticket DTO containing all fields
 */
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
    private Set<TicketMessage> ticketMessages;
}
