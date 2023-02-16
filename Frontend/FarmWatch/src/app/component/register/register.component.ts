import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../security/_services/auth.service';
import { Router } from '@angular/router';
import { NotifierService } from 'src/app/service/notifier.service';

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

  constructor(private authService: AuthService, private router: Router, private toast: NotifierService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const { firstname, lastname, email, username, password, captchaToken } = this.form;

    this.authService.register(firstname, lastname, email, username, password, captchaToken).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.toast.ShowSucces("New Notification", "Your account has been registered succesfully!")
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
        this.toast.ShowError("New Notification", "Registration failed!")
      }
    });
    setTimeout(() => {
      this.router.navigate(['']);
  }, 3000);  

  }
}