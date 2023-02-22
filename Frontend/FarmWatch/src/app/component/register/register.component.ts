import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../security/_services/auth.service';
import { ToastService } from 'src/app/service/toast.service';
import { MatDialog } from '@angular/material/dialog';

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

  constructor(private authService: AuthService,
              private toast: ToastService,
              private dialog: MatDialog) { }

  ngOnInit(): void {
  }

  onSubmit(): void {    
    const { firstname, lastname, email, username, password, captchaToken } = this.form;

    this.authService.register(firstname, lastname, email, username, password, captchaToken).subscribe({
      next: data => {
        console.log(data);
        this.dialog.closeAll();
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
  }
}