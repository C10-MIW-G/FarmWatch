import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StorageService } from './storage.service';

const AUTH_API = 'http://localhost:8080/api/v1/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient, private storageService: StorageService) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'authenticate',
      {
        username,
        password,
      },
      httpOptions
    );
  }

  register(fullName: string, email: string, username: string, password: string, captchaToken : String): Observable<any> {
    return this.http.post(
      AUTH_API + 'register',
      {
        fullName,
        email,
        username,
        password,
        captchaToken,
      },
      httpOptions
    );
  }

  confirmRegistration(token: string): Observable<any> {
    return this.http.get(
      AUTH_API + 'registrationConfirm?token=' + token,
      {responseType: 'text'}
    );
  }




  logout(){
    this.storageService.clean();
  }

}
