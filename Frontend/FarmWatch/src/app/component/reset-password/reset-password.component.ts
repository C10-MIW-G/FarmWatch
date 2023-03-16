import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/security/_services/auth.service';
import { ToastService } from 'src/app/service/toast.service';
import { LoginComponent } from '../login/login.component';
import { CustomValidators } from './custom-validators';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit{
  token!: string;
  passwordsMatching = false;
  form = new FormGroup(
    {
      password: new FormControl('', [Validators.required]),
      confirmPassword: new FormControl('', [Validators.required]),
    },
    [CustomValidators.MatchValidator('password', 'confirmPassword')]
  );

  constructor(private authService : AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private toast:ToastService,
    private dialog: MatDialog) {} 

  ngOnInit(){
    this.route.queryParamMap.subscribe((queryParams) => {
      const readTokenParam = queryParams.get('token');
      if(readTokenParam){
        this.token = readTokenParam;
      }
    });
  }

  onSubmit() {
    let password = this.form.get('password')!.value;
    this.authService.resetPassword(password!, this.token).subscribe({
      next: () => {
        this.toast.ShowSucces("New notification", "Succesfully changed the password");
        setTimeout(() => {
          this.dialog.open(LoginComponent);
          this.router.navigate(['/']);
        }, 3000); 
    },
      error: error => {
        if(error.error.message != null){
          this.toast.ShowError("New Notification", error.error.message);
        } else {
          this.toast.ShowError("New Notification", error.error);
        }
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
