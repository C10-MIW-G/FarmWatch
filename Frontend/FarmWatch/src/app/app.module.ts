import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AnimalOverviewComponent } from './animal-overview/animal-overview.component';
import { AnimalDetailComponent } from './animal-detail/animal-detail.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { httpInterceptorProviders } from './security/_helpers/http.interceptor';
import { AuthGuard } from './security/_auth/auth.guard';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AddAnimalComponent } from './add-animal/add-animal.component';
import { UpdateAnimalComponent } from './update-animal/update-animal.component';

@NgModule({
  declarations: [
    AppComponent,
    AnimalOverviewComponent,
    AnimalDetailComponent,
    AddAnimalComponent, 
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    AdminDashboardComponent,
    UpdateAnimalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [httpInterceptorProviders, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
