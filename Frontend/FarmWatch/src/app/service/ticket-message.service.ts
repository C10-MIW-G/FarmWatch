import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Ticket } from '../model/ticket';
import { TicketMessage } from '../model/ticket-message';

@Injectable({providedIn: 'root'})
export class TicketMessageService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getTicketMessage(id: number): Observable<TicketMessage> {
    return this.http.get<TicketMessage>(`${this.apiServerUrl}/ticket/message/${id}`)
  }
  
}