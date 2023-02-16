package com.theteapottroopers.farmwatch.model;

import com.theteapottroopers.farmwatch.security.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * This class contains data about a ticket message
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TicketMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "send_by_user_id", nullable = false)
    private User sendBy;
    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private final LocalDateTime messageDateTime = LocalDateTime.now();
    @Column(nullable = false)
    private String message;
    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;
}

