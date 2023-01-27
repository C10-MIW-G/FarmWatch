import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Animal } from './animal';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class AnimalService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getAnimals(): Observable<Animal[]>{
    return this.http.get<Animal[]>(`${this.apiServerUrl}/animal/all`)
  }

  public getAnimal(uuid: string): Observable<Animal> {
    return this.http.get<Animal>(`${this.apiServerUrl}/animal/find/${uuid}`)
  }
}