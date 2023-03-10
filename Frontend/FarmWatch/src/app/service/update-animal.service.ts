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

  public updateAnimal(animal: AnimalDetail): Observable<AnimalDetail>{
    return this.http.put<AnimalDetail>(`${this.apiServerUrl}/animal`, animal);
  }

  public getAnimalDetail(id: number): Observable<AnimalDetail> {
    return this.http.get<AnimalDetail>(`${this.apiServerUrl}/animal/${id}`)
  }

  public deleteAnimal(AnimalDetailId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/animal/${AnimalDetailId}`);
  }
}