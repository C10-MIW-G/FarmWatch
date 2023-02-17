import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Ticket } from '../model/ticket';
import { TicketUpdate } from '../model/ticket-update';
import { Status } from '../model/status';

@Injectable({providedIn: 'root'})
export class TicketDetailService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getTicket(id: number): Observable<Ticket> {
    return this.http.get<Ticket>(`${this.apiServerUrl}/ticket/${id}`)
  }

  public getLeanTicket(id: number): Observable<TicketUpdate> {
    return this.http.get<TicketUpdate>(`${this.apiServerUrl}/ticket/update/${id}`)
  }

  public getStatuses(): Observable<Status[]> {
    return this.http.get<Status[]>(`${this.apiServerUrl}/ticket/status`)
  }
  
}