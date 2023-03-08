import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/security/_services/auth.service';
import { DialogService } from 'src/app/service/dialog.service';
import { ToastService } from 'src/app/service/toast.service';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-account-activation',
  templateUrl: './account-activation.component.html',
  styleUrls: ['./account-activation.component.css']
})
export class AccountActivationComponent implements OnInit {

  public token!: string;

  constructor(
    private route: ActivatedRoute, 
    private router: Router,
    private toast: ToastService,
    private authService: AuthService,
    private dialog: DialogService){
  }
  ngOnInit(){
    this.route.queryParamMap.subscribe((queryParams) => {
      const readTokenParam = queryParams.get('token');
      if(readTokenParam){
        this.token = readTokenParam;
      }
    });

    this.authService.confirmRegistration(this.token).subscribe({
      next: data => {
        this.toast.ShowSucces("New Notification", data);
        setTimeout(() => {
          this.dialog.open(LoginComponent);
          this.router.navigate(['/']);
        }, 1000); 
      },
      error: err => {
        this.toast.ShowError("New Notification", err.message);
        setTimeout(() => {
          this.router.navigate(['/']);
        }, 1000); 
      }
    });

    console.log(this.token);
  }
}
