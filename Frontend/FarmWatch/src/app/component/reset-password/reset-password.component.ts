import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/security/_services/auth.service';
import { ToastService } from 'src/app/service/toast.service';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit{
  form: any = {
    password: null,
    confirmPassword: null,
  }
  token!: string;
  passwordsMatching = false;

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
    const {password} = this.form;
    this.authService.resetPassword(password, this.token).subscribe({
      next: () => {
        this.toast.ShowSucces("New notification", "Succesfully changed the password");
        setTimeout(() => {
          this.dialog.open(LoginComponent);
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
}
