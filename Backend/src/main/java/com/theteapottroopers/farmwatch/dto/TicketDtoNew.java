package com.theteapottroopers.farmwatch.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * Ticket DTO containing the fields to create a new ticket
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TicketDtoNew {

    private String title;
    private String description;
    private String reportUsername;
    private Long animalId;
}
