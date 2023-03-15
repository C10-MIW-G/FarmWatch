import { NgModule } from '@angular/core';
import { RouterModule, Routes, ExtraOptions } from '@angular/router';
import { AddAnimalComponent } from './component/add-animal/add-animal.component';
import { AnimalDetailComponent } from './component/animal-detail/animal-detail.component';
import { AnimalOverviewComponent } from './component/animal-overview/animal-overview.component';
import { LoginComponent } from './component/login/login.component';
import { ProfileComponent } from './component/profile/profile.component';
import { RegisterComponent } from './component/register/register.component';
import { AuthGuard } from './security/_auth/auth.guard';
import { UpdateAnimalComponent } from './component/update-animal/update-animal.component';
import { AdminDashboardComponent } from './component/admin-dashboard/admin-dashboard.component';
import { CreateTicketComponent } from './component/create-ticket/create-ticket.component';
import { TicketDetailComponent } from './component/ticket-detail/ticket-detail.component';
import { UserUpdateComponent } from './component/user-update/user-update.component';
import { UserDetailComponent } from './component/user-detail/user-detail.component';
import { TicketOverviewComponent } from './component/ticket-overview/ticket-overview.component';
import { TicketUpdateComponent } from './component/ticket-update/ticket-update.component';
import { AccountActivationComponent } from './component/account-activation/account-activation.component';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './component/reset-password/reset-password.component';
import { AnimalOverviewImageComponent } from './component/animal-oveview-image/animal-overview-image.component';

const routes: Routes = [
  { path: '', component: AnimalOverviewImageComponent},
  { path: 'animal', component: AddAnimalComponent, canActivate: [AuthGuard], data: {role: ['ADMIN', 'CARETAKER']}},
  { path: 'animal/table', component: AnimalOverviewComponent, canActivate: [AuthGuard], data: {role: ['ADMIN', 'CARETAKER']}},
  { path: 'animal/:id', component: AnimalDetailComponent},
  { path: 'animal/update/:id', component: UpdateAnimalComponent, canActivate: [AuthGuard], data: {role: ['ADMIN', 'CARETAKER']}},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent},
  { path: 'admindashboard', component: AdminDashboardComponent, canActivate: [AuthGuard], data: {role: ['ADMIN']}},
  { path: 'user/update',component: UserUpdateComponent, canActivate: [AuthGuard], data: {role: ['ADMIN','USER', 'CARETAKER']}},
  { path: 'user/details',component: UserDetailComponent, canActivate: [AuthGuard], data: {role: ['ADMIN','USER', 'CARETAKER']}},
  { path: 'user/update/:id', component: UserUpdateComponent, canActivate: [AuthGuard], data: {role: ['ADMIN']}},
  { path: 'user/:id', component: UserDetailComponent, canActivate: [AuthGuard], data: {role: ['ADMIN']}},
  { path: 'ticket/new', component: CreateTicketComponent, canActivate: [AuthGuard], data: {role: ['ADMIN', 'USER', 'CARETAKER']}},
  { path: 'ticket/update/:id', component: TicketUpdateComponent, canActivate: [AuthGuard], data: {role: ['ADMIN', 'CARETAKER']}},
  { path: 'ticket/:id', component: TicketDetailComponent, canActivate: [AuthGuard], data: {role: ['USER', 'ADMIN', 'CARETAKER']}},
  { path: 'ticket', component: TicketOverviewComponent, canActivate: [AuthGuard], data: {role: ['USER', 'ADMIN', 'CARETAKER']}},
  { path: 'registrationConfirmation', component: AccountActivationComponent},
  { path: 'forgotPassword', component: ForgotPasswordComponent},
  { path: 'resetPassword', component: ResetPasswordComponent},
  { path: 'registrationConfirmation', component: AccountActivationComponent},
];

const routerOptions: ExtraOptions = {
  anchorScrolling: 'enabled',
  onSameUrlNavigation: 'reload',
};


@NgModule({
  imports: [RouterModule.forRoot(routes, routerOptions)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
