import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../security/_services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {
  form: any = {
    firstname: null,
    lastname: null,
    username: null,
    email: null,
    password: null,
    captchaToken: null,
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const { firstname, lastname, email, username, password, captchaToken } = this.form;

    this.authService.register(firstname, lastname, email, username, password, captchaToken).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    });
    setTimeout(() => {
      this.router.navigate(['login']);
  }, 1000);  

  }
}