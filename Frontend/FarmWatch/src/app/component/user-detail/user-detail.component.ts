import { Component } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/model/user';
import { ActivatedRoute } from '@angular/router';
import { StorageService } from 'src/app/security/_services/storage.service';
import { HttpErrorResponse} from '@angular/common/http';
import { Location } from '@angular/common';
import { ToastService } from 'src/app/service/toast.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent {
  public user?: User;
  public id!: number;
  public currentPath: String;
  public userPagePath: String = "details";
  public updatePath: String = "/user/update";
  public isAdmin: boolean = false; 
  public formattedRole: string = "";

  constructor(private userDetailService : UserService, 
    private route: ActivatedRoute, 
    private storageService: StorageService,
    private location: Location,
    private toast: ToastService) {
      const pathArray = location.path().split('/');
      this.currentPath = pathArray.pop()!;
      if(this.currentPath === this.userPagePath){
        this.id = storageService.getUser().id;
      } else {
        this.route.params.subscribe(params => {
          this.id = params['id'];
          this.updatePath = this.updatePath + "/" + this.id.toString();
        });
      }  
  }

  ngOnInit(): void {
    this.getUser(this.id);
  }

  public getUser(id: number): void {
    this.userDetailService.getUserDetail(id).subscribe(
      (response: User) => {
        this.user = response;
        if (this.user.role === 'ROLE_USER'){
          this.formattedRole = 'User'
        } else if (this.user.role === 'ROLE_CARETAKER'){
          this.formattedRole = 'Caretaker'
        } else {
          this.formattedRole = 'Administrator'
        }
        if(this.storageService.getRole() == 'ADMIN'){
          this.isAdmin = true; 
        }
      },
      (error: HttpErrorResponse) => {
        if(error.error.message != null){
          this.toast.ShowError("New Notification", "Please login again");
        } else {
          this.toast.ShowError("New Notification", error.error);
        }
      }
    );
  }

  public fullName():String {
    const fullname: string = this.user ? `${this.user.firstname} ${this.user.lastname}` : '';
    return fullname;
  }

}
