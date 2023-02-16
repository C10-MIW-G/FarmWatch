package com.theteapottroopers.farmwatch.repository;

import com.theteapottroopers.farmwatch.model.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
