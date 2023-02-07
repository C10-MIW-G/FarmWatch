package com.theteapottroopers.farmwatch.repository;

import com.theteapottroopers.farmwatch.model.TicketMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketMessageRepository extends JpaRepository<TicketMessage, Long> {
}
