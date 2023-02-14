export interface TicketMessage {
    id: number;
    sendByUser: {
        id: number;
        username: string;
    };
    messageLocalDateTime: string;
    message: string;
    ticketId: number;
}