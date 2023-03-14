import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/security/_services/auth.service';
import { StorageService } from 'src/app/security/_services/storage.service';
import { ToastService } from 'src/app/service/toast.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit{

  form: any = {
    email: null
  };

  ngOnInit(){
  }

  constructor(private authService: AuthService, 
    private storageService: StorageService, 
    private router: Router,
    private toast: ToastService,
    private dialog: MatDialog 
    ) { }
    
    onSubmit(): void {
      const { email } = this.form;
  
      this.authService.forgotPassword(email).subscribe({
        next: data => {
          this.dialog.closeAll();
          this.toast.ShowSucces("New Notification", "Please check your email to reset your password");
        },
        error: err => {
          this.toast.ShowError("New Notification", "Something went wrong, please try again");
        }
      });
    }
}
