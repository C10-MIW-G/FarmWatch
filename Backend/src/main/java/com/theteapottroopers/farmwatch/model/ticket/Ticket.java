package com.theteapottroopers.farmwatch.model.ticket;

import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.model.ImageData;
import com.theteapottroopers.farmwatch.security.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

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
    @NotNull
    @Column(nullable = false)
    private String summary;
    @NotNull
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
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
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.REMOVE)
    private List<TicketMessage> ticketMessages;
    @OneToOne(cascade = CascadeType.REMOVE)
    private ImageData image;

    public boolean isTicketFromUserId(Long id){
        return reportedBy.getId().equals(id);
    }
    public boolean hasPrivateMessages(){
        for(TicketMessage ticketMessage : ticketMessages){
            if(ticketMessage.isPrivateMessage()){
                return true;
            }
        }
        return false;
    }
}