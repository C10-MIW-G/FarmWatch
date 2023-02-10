import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class UpdateUserService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public updateUser(user: User): Observable<User>{
    return this.http.put<User>(`${this.apiServerUrl}/user/update`, user);
  }
}