package com.theteapottroopers.farmwatch.repository;

import com.theteapottroopers.farmwatch.model.ticket.TicketMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketMessageRepository extends JpaRepository<TicketMessage, Long> {
}
