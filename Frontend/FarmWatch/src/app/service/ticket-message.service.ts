import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Ticket } from '../model/ticket';
import { TicketMessage } from '../model/ticket-message';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({providedIn: 'root'})
export class TicketMessageService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getTicketMessage(id: number): Observable<TicketMessage> {
    return this.http.get<TicketMessage>(`${this.apiServerUrl}/ticket/message/${id}`)
  }

  public addTicketMessage(data: any): Observable<any>{
    return this.http.post(`${this.apiServerUrl}/ticket/message`, data);
  }
}