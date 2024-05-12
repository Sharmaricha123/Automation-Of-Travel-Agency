import { forwardRef, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { FormsModule, NG_VALUE_ACCESSOR } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import { DemoNgZorroAntdModule } from './DemoNgZorroAntdModule';
import { ReactiveFormsModule } from '@angular/forms';
import { SignupComponent } from './signup/signup.component';
import { NavbarComponent } from './navbar/navbar.component';
import { Dashboard1Component } from './dashboard1/dashboard1.component';
import { ChangepasswordComponent } from './changepassword/changepassword.component';


import { TokenInterceptorService } from '../services/token-interceptor.service';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDialogModule} from '@angular/material/dialog';
import { NzModalService } from 'ng-zorro-antd/modal';

import { MatTableModule } from '@angular/material/table'  
import { MatInputModule } from '@angular/material/input';


import { MatOptionModule } from '@angular/material/core';

import {MatSlideToggleModule} from '@angular/material/slide-toggle';

import {MatSelectModule} from '@angular/material/select';
import { ManageDriversComponent } from './manage-drivers/manage-drivers.component';
import { ManageRoutesComponent } from './manage-routes/manage-routes.component';
import { ManageVehicleComponent } from './manage-vehicle/manage-vehicle.component';
import { ManageBookingComponent } from './manage-booking/manage-booking.component';
import { AddVehicleComponent } from './add-vehicle/add-vehicle.component';
import { AddDriverComponent } from './add-driver/add-driver.component';
import { AddRouteComponent } from './add-route/add-route.component';
import { CreatebookingComponent } from './createbooking/createbooking.component';
import { MyBookingsComponent } from './my-bookings/my-bookings.component';
import { ConfrimbookingComponent } from './confrimbooking/confrimbooking.component';

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    NavbarComponent,
    Dashboard1Component,
    ChangepasswordComponent,
    ManageDriversComponent,
    ManageRoutesComponent,
    ManageVehicleComponent,
    ManageBookingComponent,
    AddVehicleComponent,
    AddDriverComponent,
    AddRouteComponent,
    CreatebookingComponent,
    MyBookingsComponent,
    ConfrimbookingComponent,
  
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    DemoNgZorroAntdModule,
    ReactiveFormsModule,
    MatCardModule,
    MatToolbarModule,
    FormsModule, 
    MatButtonModule,
    MatIconModule, 
    MatFormFieldModule,
    MatDialogModule,
    MatTableModule,
    MatInputModule,
    MatOptionModule,
    MatSlideToggleModule,
    MatSelectModule
   
  ],

  providers: [
    { provide: NZ_I18N, useValue: en_US },
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService , multi: true},
   
  ],
  bootstrap: [AppComponent]
})
export class AppModule { 

}
