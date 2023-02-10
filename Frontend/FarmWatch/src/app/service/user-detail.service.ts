import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class UserDetailService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getUserDetail(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiServerUrl}/user/${id}`)
  }

  
}