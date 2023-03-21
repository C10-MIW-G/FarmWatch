package com.theteapottroopers.farmwatch;

import com.theteapottroopers.farmwatch.exception.FieldHasNoInputException;
import com.theteapottroopers.farmwatch.model.Animal;
import com.theteapottroopers.farmwatch.model.ticket.Ticket;
import com.theteapottroopers.farmwatch.model.ticket.TicketMessage;
import com.theteapottroopers.farmwatch.model.ticket.TicketStatus;
import com.theteapottroopers.farmwatch.repository.TicketMessageRepository;
import com.theteapottroopers.farmwatch.repository.TicketRepository;
import com.theteapottroopers.farmwatch.service.TicketService;
import com.theteapottroopers.farmwatch.validation.TicketValidation;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TicketServiceTests {
    
    @Spy
    private TicketRepository ticketRepository;

    @Spy
    private TicketValidation ticketValidation;
    
    @InjectMocks
    private TicketService ticketService;

    @Test
    void savedTicketHasAnimal() {
        Animal animal = new Animal("Name", "Common_Name", "Species", "Description", LocalDate.now(), null);
        Ticket ticket = Ticket.builder()
                .animal(animal)
                .summary("")
                .description("")
                .status(TicketStatus.OPEN)
                .assignedTo(null)
                .reportedBy(null)
                .ticketMessages(null)
                .image(null)
                .build();

        when(ticketRepository.findById(anyLong())).thenReturn(Optional.ofNullable(ticket));

        Ticket ticketFromService = ticketService.findTicketById(1L);
        assertNotNull(ticketFromService.getAnimal());
    }

    @Test
    void AddTicketWithNullSummaryShouldThrowException(){
        Ticket ticket = Ticket.builder()
                .summary(null)
                .description("Test description")
                .build();

        FieldHasNoInputException exception = assertThrows(FieldHasNoInputException.class, () -> ticketService.addTicket(ticket));
        assertEquals(exception.getMessage(), "Summary can't be empty");
    }

    @Test
    void AddTicketWithNullDescriptionShouldThrowException(){
        Ticket ticket = Ticket.builder()
                .summary("Test Summary")
                .description(null)
                .build();

        FieldHasNoInputException exception = assertThrows(FieldHasNoInputException.class, () -> ticketService.addTicket(ticket));
        assertEquals(exception.getMessage(), "Description can't be be empty");
    }

    @Test
    void savedTicketHasNoAnimal() {
        Ticket ticket = Ticket.builder()
                .animal(null)
                .summary("Test Ticket")
                .description("This is a test Ticket")
                .status(TicketStatus.OPEN)
                .assignedTo(null)
                .reportedBy(null)
                .ticketMessages(null)
                .image(null)
                .build();

        when(ticketRepository.findById(anyLong())).thenReturn(Optional.ofNullable(ticket));

        Ticket ticketFromService = ticketService.findTicketById(1L);
        assertNull(ticketFromService.getAnimal());
    }

    @Test
    void savedTicketHasPrivateMessages(){
        Ticket ticket = Ticket.builder()
                .animal(null)
                .summary("Test Ticket")
                .description("This is a test Ticket")
                .status(TicketStatus.OPEN)
                .assignedTo(null)
                .reportedBy(null)
                .ticketMessages(null)
                .image(null)
                .build();
        TicketMessage ticketMessage = TicketMessage.builder()
                .ticket(ticket)
                .message("")
                .privateMessage(true)
                .sendBy(null)
                .build();
        ArrayList<TicketMessage> ticketMessages = new ArrayList<>();
        ticketMessages.add(ticketMessage);
        ticket.setTicketMessages(ticketMessages);

        when(ticketRepository.findById(anyLong())).thenReturn(Optional.ofNullable(ticket));

        Ticket ticketFromService = ticketService.findTicketById(1L);
        assertTrue(ticketFromService.hasPrivateMessages());

    }
}




