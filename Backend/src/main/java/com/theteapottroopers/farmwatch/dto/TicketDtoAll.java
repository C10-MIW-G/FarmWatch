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
    private String subject;
    private String description;
    private String status;
    private LocalDateTime reportDateTime;
    private AnimalDtoAnimalName animal;
    private UserDtoUsername reportedByUser;
    private UserDtoUsername assignedToUser;
    private List<Long> ticketMessageIds;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDtoUsername{
        private Long id;
        private String username;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AnimalDtoAnimalName{
        private Long id;
        private String name;
    }
}
