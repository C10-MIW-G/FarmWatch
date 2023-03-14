import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { environment } from 'src/environments/environment';
import { CaretakerOverview } from '../model/caretaker-overview';

@Injectable({providedIn: 'root'})
export class UserService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getUserDetail(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiServerUrl}/user/${id}`)
  }

  public updateUser(user: User): Observable<User>{
    return this.http.put<User>(`${this.apiServerUrl}/user/update`, user);
  }

  public getCaretakers(): Observable<CaretakerOverview[]> {
    return this.http.get<CaretakerOverview[]>(`${this.apiServerUrl}/user/caretakers`);
  }
}