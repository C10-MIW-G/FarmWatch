import { Component, OnInit } from '@angular/core';
import { AuthService } from '../security/_services/auth.service';
import { StorageService } from '../security/_services/storage.service';
import { AppComponent } from '../app.component';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    password: null,
    token: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  captchaToken: string|undefined;

  constructor(private authService: AuthService, private storageService: StorageService) {  this.captchaToken = undefined }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().role;
    }
  }

  onSubmit(): void {
    const { username, password, captchaToken} = this.form;
    this.authService.login(username, password, captchaToken).subscribe({
      next: data => {
        this.storageService.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.storageService.getUser().role;
        this.reloadPage();
      },
      error: err => {
        this.errorMessage = err;
        this.isLoginFailed = true;
      }
    });
  }

  public send(form: NgForm): void {
    
    if (form.invalid) {
      for (const control of Object.keys(form.controls)) {
        form.controls[control].markAsTouched();
      }
      return;
    }
  }

  reloadPage(): void {
    window.location.reload();
  }
}
