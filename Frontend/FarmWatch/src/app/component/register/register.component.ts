import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../security/_services/auth.service';
import { ToastService } from 'src/app/service/toast.service';
import { MatDialog } from '@angular/material/dialog';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CustomValidators } from '../reset-password/custom-validators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {
  form = new FormGroup(
    {
      fullname: new FormControl('', [Validators.required]),
      username: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      captchaToken: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      confirmPassword: new FormControl('', [Validators.required]),
    },
    [CustomValidators.MatchValidator('password', 'confirmPassword')]
  );
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService,
              private toast: ToastService,
              private dialog: MatDialog) { }

  ngOnInit(): void {
  }

  onSubmit(): void {    
    let fullname = this.form.get('fullname')!.value;
    let email = this.form.get('email')!.value;
    let username = this.form.get('username')!.value;
    let password = this.form.get('password')!.value;
    let captchaToken = this.form.get('captchaToken')!.value;

    this.authService.register(fullname!, email!, username!, password!, captchaToken!).subscribe({
      next: data => {
        console.log(data);
        this.dialog.closeAll();
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.toast.ShowSucces("New Notification", "Registration completed. Please check your email")
      },
      error: err => {
        this.errorMessage = err.error;
        this.isSignUpFailed = true;
        this.toast.ShowError("New Notification", this.errorMessage)
      }
    });
  }

  get passwordMatchError() {
    return (
      this.form.getError('mismatch') &&
      this.form.get('confirmPassword')?.touched
    );
  }
}