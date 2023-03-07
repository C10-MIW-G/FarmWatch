import { Pipe, PipeTransform } from '@angular/core';
import { Ticket } from '../model/ticket';

@Pipe({
  name: 'personalTicketFilter'
})
export class PersonalTicketFilter implements PipeTransform {
  transform(tickets: Ticket[], searchTerm: string): Ticket[] {
    if (!tickets || !searchTerm) {
      return tickets;
    }

    return tickets.filter(ticket => {
      const term = searchTerm;
      return (
        ticket.assignedToUser?.username?.toLowerCase() === term.toLowerCase()
      );
    });
  }
}