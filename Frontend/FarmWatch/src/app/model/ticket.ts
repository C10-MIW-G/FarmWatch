export interface Ticket {
    id: number;
    subject: string;
    description: string;
    status: string;
    reportDateTime: string;
    animal: {
        id: number;
        name: string;    
    };
    reportedByUser: {
        id: number;
        username: string; 
    };
    assignedToUser: {
        id: number;
        username: string; 
    };
    ticketMessageIds: number[];
}

