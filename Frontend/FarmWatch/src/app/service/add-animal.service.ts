import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({providedIn: 'root'})
export class AddAnimalService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public createAnimal(
    name: string,
    commonName: string,
    species: string,
    description: string,
    dateOfBirth: string,
    imageFileName: string): Observable<any>{
    return this.http.post(`${this.apiServerUrl}/animal`, 
      {
        name,
        commonName,
        species,
        description,
        dateOfBirth,
        imageFileName
      },
      
      httpOptions
    );
  }
}