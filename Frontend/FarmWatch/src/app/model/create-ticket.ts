export interface CreateTicket {
    summary: string;
    description: string;
    reportedByUserId: number;
    animalId: number;
    imageFileName: string;
}