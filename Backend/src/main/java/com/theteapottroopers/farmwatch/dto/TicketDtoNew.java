package com.theteapottroopers.farmwatch.dto;

import lombok.*;

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

    private String summary;
    private String description;
    private String reportUsername;
    private Long animalId;
}
