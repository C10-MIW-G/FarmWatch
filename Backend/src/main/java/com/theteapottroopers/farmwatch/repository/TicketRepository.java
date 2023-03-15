package com.theteapottroopers.farmwatch.repository;

import com.theteapottroopers.farmwatch.model.ticket.Ticket;
import com.theteapottroopers.farmwatch.model.ticket.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    int countByAnimalIdAndStatusIn(Long animalId, List<TicketStatus> statuses);

}
