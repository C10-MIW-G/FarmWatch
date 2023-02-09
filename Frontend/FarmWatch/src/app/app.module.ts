import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AnimalOverviewComponent } from './component/animal-overview/animal-overview.component';
import { AnimalDetailComponent } from './component/animal-detail/animal-detail.component';
import { LoginComponent } from './component/login/login.component';
import { ProfileComponent } from './component/profile/profile.component';
import { RegisterComponent } from './component/register/register.component';
import { httpInterceptorProviders } from './security/_helpers/http.interceptor';
import { AuthGuard } from './security/_auth/auth.guard';
import { AdminDashboardComponent } from './component/admin-dashboard/admin-dashboard.component';
import { AddAnimalComponent } from './component/add-animal/add-animal.component';
import { UpdateAnimalComponent } from './component/update-animal/update-animal.component';
import { environment } from '../environments/environment';
import { RECAPTCHA_SETTINGS, RecaptchaFormsModule, RecaptchaModule, RecaptchaSettings } from 'ng-recaptcha';
import { MatSidenavModule } from '@angular/material/sidenav';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserDetailComponent } from './component/user-detail/user-detail.component';



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
    UpdateAnimalComponent,
    UserDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RecaptchaModule,
    RecaptchaFormsModule,
    MatSidenavModule,
    BrowserAnimationsModule
  ],
  providers: [httpInterceptorProviders, AuthGuard , {
    provide: RECAPTCHA_SETTINGS,
    useValue: {
      siteKey: environment.recaptcha.siteKey,
    } as RecaptchaSettings,}], 
    bootstrap: [AppComponent]
})
export class AppModule { }
