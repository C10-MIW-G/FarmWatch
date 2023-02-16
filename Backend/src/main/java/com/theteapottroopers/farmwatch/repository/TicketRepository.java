package com.theteapottroopers.farmwatch.repository;

import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
