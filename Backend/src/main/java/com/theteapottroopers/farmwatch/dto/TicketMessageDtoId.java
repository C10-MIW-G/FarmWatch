package com.theteapottroopers.farmwatch.dto;

import lombok.*;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * TicketMessage DTO containing only the id
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TicketMessageDtoId {
    private Long id;
}
