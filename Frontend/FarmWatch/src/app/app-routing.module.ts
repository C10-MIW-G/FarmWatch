import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddAnimalComponent } from './add-animal/add-animal.component';
import { AnimalDetailComponent } from './animal-detail/animal-detail.component';
import { AnimalOverviewComponent } from './animal-overview/animal-overview.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { AuthGuard } from './_auth/auth.guard';
import { UpdateAnimalComponent } from './update-animal/update-animal.component';

const routes: Routes = [
  { path: '', component: AnimalOverviewComponent },
  { path: 'animal/:id', component: AnimalDetailComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'animal', component: AddAnimalComponent, canActivate: [AuthGuard], data: {role: ['ADMIN', 'USER']}},
  { path: 'animal/update/:id', component: UpdateAnimalComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
