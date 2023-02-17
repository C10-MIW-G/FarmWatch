import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AnimalDetail } from '../model/animal-detail';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class AnimalDetailService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getAnimalDetail(id: number): Observable<AnimalDetail> {
    return this.http.get<AnimalDetail>(`${this.apiServerUrl}/animal/${id}`)
  }

  public deleteAnimal(AnimalDetailId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/animal/${AnimalDetailId}`);
  }
  
  
}