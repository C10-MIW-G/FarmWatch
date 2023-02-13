import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Ticket } from '../model/ticket';

@Injectable({providedIn: 'root'})
export class TicketDetailService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getTicket(id: number): Observable<Ticket> {
    return this.http.get<Ticket>(`${this.apiServerUrl}/ticket/${id}`)
  }
  
}