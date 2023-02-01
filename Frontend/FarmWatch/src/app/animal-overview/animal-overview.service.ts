import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AnimalOverview } from './animal-overview';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class AnimalOverviewService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getAnimals(): Observable<AnimalOverview[]>{
    return this.http.get<AnimalOverview[]>(`${this.apiServerUrl}/animal`)
  }

}