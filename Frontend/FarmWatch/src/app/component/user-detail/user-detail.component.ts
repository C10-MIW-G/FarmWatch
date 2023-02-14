import { Component } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/model/user';
import { ActivatedRoute } from '@angular/router';
import { StorageService } from 'src/app/security/_services/storage.service';
import { HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent {
  public user?: User;
  public id!: number;

  constructor(private userDetailService : UserService, 
    private route: ActivatedRoute, 
    private storageService: StorageService) {
    this.id = storageService.getUser().id;
  }

  ngOnInit(): void {
    this.getUser(this.id);
  }

  public getUser(id: number): void {
    this.userDetailService.getUserDetail(id).subscribe(
      (response: User) => {
        this.user = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
