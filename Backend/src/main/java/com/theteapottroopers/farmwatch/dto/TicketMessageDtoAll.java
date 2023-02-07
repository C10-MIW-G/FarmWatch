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
    private Long sendByUserId;
    private LocalDateTime messageLocalDateTime;
    private String message;
    private Long ticketId;
}
