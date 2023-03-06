export interface TicketMessage {
    id: number;
    sendByUser: {
        id: number;
        username: string;
        role: string;
    };
    messageLocalDateTime: string;
    message: string;
    ticketId: number;
    privateMessage: boolean;
}