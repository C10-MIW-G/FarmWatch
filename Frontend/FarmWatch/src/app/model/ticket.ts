export interface Ticket {
    id: number;
    title: string;
    description: string;
    status: string;
    reportDateTime: string;
    animalId: number;
    reportedByUserId: number;
    assignedToUserId: number;
    ticketMessageIds: number[];
}