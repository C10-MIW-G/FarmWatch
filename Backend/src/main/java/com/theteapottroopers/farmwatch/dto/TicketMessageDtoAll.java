package com.theteapottroopers.farmwatch.dto;

import lombok.*;
import java.time.LocalDateTime;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * TicketMessage DTO containing all fields
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TicketMessageDtoAll {
    private Long id;
    private UserDtoUsername sendByUser;
    private LocalDateTime messageLocalDateTime;
    private String message;
    private Long ticketId;
    private boolean privateMessage;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDtoUsername{
        private Long id;
        private String username;
        private String role;
    }
}
