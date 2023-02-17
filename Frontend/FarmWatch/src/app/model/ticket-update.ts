export interface TicketUpdate {
    id: number;
    summary: string;
    description: string;
    status: string;
    assignedTo: number | null;
    assignedToName: string;
}
