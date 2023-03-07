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

  createTicket(summary: string, description: string, animalId: number, reportUsername: string, imageFileName: string): Observable<any> {
    return this.http.post(
      this.apiServerUrl + '/ticket',
      {
        summary, 
        description,
        animalId,
        reportUsername,
        imageFileName
      },
      httpOptions
    );
  }
}