import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { ChangepasswordComponent } from './changepassword/changepassword.component';
import { Dashboard1Component } from './dashboard1/dashboard1.component';

import { NoAuthGuard } from './no-auth.guard';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { ManageDriversComponent } from './manage-drivers/manage-drivers.component';
import { ManageRoutesComponent } from './manage-routes/manage-routes.component';
import { ManageVehicleComponent } from './manage-vehicle/manage-vehicle.component';
import { ManageBookingComponent } from './manage-booking/manage-booking.component';
import { AddDriverComponent } from './add-driver/add-driver.component';
import { AddVehicleComponent } from './add-vehicle/add-vehicle.component';
import { AddRouteComponent } from './add-route/add-route.component';
import { CreatebookingComponent } from './createbooking/createbooking.component';
import { MyBookingsComponent } from './my-bookings/my-bookings.component';
import { ConfrimbookingComponent } from './confrimbooking/confrimbooking.component';

const routes: Routes = [{path: 'login',component:LoginComponent,canActivate:[NoAuthGuard]},
                        {path: 'register',component:SignupComponent,canActivate:[NoAuthGuard]},
                        {path: 'dashboard',component:Dashboard1Component,canActivate:[AuthGuard],
                        children: 
                        [
                          {
                            path:'changepassword', component: ChangepasswordComponent
                          },{
                            path:'adddriver', component: AddDriverComponent
                          },{
                            path:'addvehicle', component: AddVehicleComponent
                          },{
                            path:'mybookings', component: MyBookingsComponent
                          },{
                            path:'confirmbookings', component: ConfrimbookingComponent
                          },
                          {
                            path:'addroute', component: AddRouteComponent
                          }, {
                            path:'createbooking', component: CreatebookingComponent
                          },
                          {
                            path:'managedrivers', component: ManageDriversComponent
                          }, {
                            path:'manageroutes', component: ManageRoutesComponent
                          },{
                            path:'managevehicles', component: ManageVehicleComponent
                          },{
                            path:'managebookings', component: ManageBookingComponent
                          }
                        ]   
                        } ];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
