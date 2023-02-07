package com.theteapottroopers.farmwatch.model;

import com.theteapottroopers.farmwatch.security.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * @author Dave Thijs <d.thijs@st.hanze.nl>
 * <p>
 * This class contains data about a ticket
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private final LocalDateTime reportDateTime = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
    @ManyToOne
    @JoinColumn(name = "reported_by_user_id", nullable = false)
    private User reportedBy;
    @ManyToOne
    @JoinColumn(name = "assigned_to_user_id")
    private User assignedTo;
    @OneToMany(mappedBy = "ticket")
    private Set<TicketMessage> ticketMessages;
}