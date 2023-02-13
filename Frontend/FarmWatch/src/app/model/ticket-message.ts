export interface TicketMessage {
    id: number;
    sendByUserId: number;
    messageLocalDateTime: string;
    message: string;
    ticketId: number;
}