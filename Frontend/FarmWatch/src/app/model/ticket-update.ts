export interface TicketUpdate {
    id: number;
    animalId: number;
    animalName: string;
    summary: string;
    description: string;
    status: string;
    assignedTo: number | null;
    assignedToName: string;
}
