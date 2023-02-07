import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AnimalOverview } from 'src/app/model/animal-overview';
import { environment } from 'src/environments/environment';
import { AnimalDetail } from 'src/app/model/animal-detail';

@Injectable({providedIn: 'root'})
export class UpdateAnimalService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public updateAnimal(animal: AnimalOverview): Observable<AnimalOverview>{
    return this.http.put<AnimalOverview>(`${this.apiServerUrl}/animal`, animal);
  }

  public getAnimalDetail(id: number): Observable<AnimalDetail> {
    return this.http.get<AnimalDetail>(`${this.apiServerUrl}/animal/${id}`)
  }

}