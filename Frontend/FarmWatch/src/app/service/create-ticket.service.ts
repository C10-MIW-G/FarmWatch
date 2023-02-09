import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

@Injectable({providedIn: 'root'})
export class CreateTicketService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  createTicket(title: string, description: string, reportUsername: string): Observable<any> {
    return this.http.post(
      this.apiServerUrl + '/ticket',
      {
        title, 
        description,
        reportUsername
      },
      httpOptions
    );
  }
}