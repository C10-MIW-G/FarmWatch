import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnimalDetailComponent } from './animal-detail/animal-detail.component';
import { AnimalOverviewComponent } from './animal-overview/animal-overview.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { AuthGuard } from './_auth/auth.guard';

const routes: Routes = [
  { path: '', component: AnimalOverviewComponent },
  { path: 'animal/:id', component: AnimalDetailComponent, canActivate: [AuthGuard], data: {role: ['ADMIN', 'USER']}},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
