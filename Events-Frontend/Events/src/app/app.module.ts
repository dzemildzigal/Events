import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MaterialsModule} from './materials/materials.module';
import { NavigationComponent } from './components/navigation/navigation.component';
import { EventsComponent } from './components/events/events.component';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { UserService } from './services/user.service';
import { TokenInterceptor } from './util/http.interceptor';
import { LocalStorageService } from './util/local-storage.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    NavigationComponent,
    EventsComponent,
    NotificationsComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MaterialsModule,
    HttpClientModule,
    
  ],
  providers: [
    UserService,
    LocalStorageService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true 
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
