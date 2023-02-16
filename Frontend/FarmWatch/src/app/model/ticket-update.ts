export interface TicketUpdate {
    id: number;
    subject: string;
    description: string;
    status: string;
    assignedTo: number;
    assignedToName: string;
}
