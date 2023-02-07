import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddAnimalComponent } from './component/add-animal/add-animal.component';
import { AnimalDetailComponent } from './component/animal-detail/animal-detail.component';
import { AnimalOverviewComponent } from './component/animal-overview/animal-overview.component';
import { LoginComponent } from './component/login/login.component';
import { ProfileComponent } from './component/profile/profile.component';
import { RegisterComponent } from './component/register/register.component';
import { AuthGuard } from './security/_auth/auth.guard';
import { UpdateAnimalComponent } from './component/update-animal/update-animal.component';
import { AdminDashboardComponent } from './component/admin-dashboard/admin-dashboard.component';

const routes: Routes = [
  { path: '', component: AnimalOverviewComponent },
  { path: 'animal/:id', component: AnimalDetailComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent, canActivate: [AuthGuard], data: {role: ['ADMIN']}},
  { path: 'animal', component: AddAnimalComponent, canActivate: [AuthGuard], data: {role: ['ADMIN']}},
  { path: 'animal/update/:id', component: UpdateAnimalComponent},
  { path: 'admindashboard', component: AdminDashboardComponent, canActivate: [AuthGuard], data: {role: ['ADMIN']}}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
