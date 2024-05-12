import { Component, OnInit } from '@angular/core';
import { FormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { BookingService } from 'src/services/booking.service';
import { RouteService } from 'src/services/route.service';
import { VehicleService } from 'src/services/vehicle.service';

@Component({
  selector: 'app-createbooking',
  templateUrl: './createbooking.component.html',
  styleUrls: ['./createbooking.component.scss']
})
export class CreatebookingComponent implements OnInit{
  constructor(private fb:FormBuilder,private bookingservice:BookingService,private vehicleservice:VehicleService,private routeservice:RouteService,private notification:NzNotificationService,private router:Router){}
  responsemessage:any;
  validateForm!: UntypedFormGroup;
  data:any;
  vehicledata:any;
  selectedvalue:any;
  selectedvalue1:any;
  totalprice:any=0;
  test:boolean=false;
  addformgroup!: UntypedFormGroup;

  ngOnInit(): void {
    
    this.addformgroup = this.fb.group({
      date: [null, [Validators.required]]})
    this.getallusers();
    this.getallvehicles();
    this.test=false;
    this.validateForm = this.fb.group({
      source: [null, [Validators.required]],
      destination: [null, [Validators.required]],
      distance: [null, [Validators.required]],
    });
  }



  getallusers(){
    this.routeservice.getdrivers().subscribe((response :any )=>{ 
      // console.log("from bookings")
      // console.log(response);
      this.data=response;
      this.responsemessage=response?.message;
      // this.notification.success('Success',this.responsemessage,{nzDuration:5000});
  
      
  
    },(error)=>{
      if(error.error?.message){
        this.responsemessage= error.error?.message;
      }
      else{
        this.responsemessage="somethingwentwrong";
      }
      this.notification.error('error',this.responsemessage,{nzDuration:5000});
    })
  }
  getallvehicles(){
    this.vehicleservice.getvehiclebystatus().subscribe((response :any )=>{ 
      console.log("from bookings")
      console.log(response);
      this.vehicledata=response;
      this.responsemessage=response?.message;
      // this.notification.success('Success',this.responsemessage,{nzDuration:5000});
    },(error)=>{
      if(error.error?.message){
        this.responsemessage= error.error?.message;
      }
      else{
        this.responsemessage="somethingwentwrong";
      }
      this.notification.error('error',this.responsemessage,{nzDuration:5000});
    })
  }
logroutes(value:any): void {
    this.selectedvalue=value
    var id= parseInt(this.selectedvalue.distance);
    this.selectedvalue.distance=id;
    this.totalprice=this.selectedvalue.distance*this.selectedvalue1.price;
    this.test=true;
  console.log(this.selectedvalue)};
log(value:any): void {
   this.selectedvalue1=value
   var id= parseInt(this.selectedvalue1.price);
   this.selectedvalue1.price=id;
   this.totalprice=this.selectedvalue.distance*this.selectedvalue1.price;
   this.test=true;
  console.log(this.selectedvalue1)};

  addroute(): void {
 
      var data = { vehicle:{vehicleid:this.selectedvalue1.vehicleid.toString()},
                  route:{routeid:this.selectedvalue.routeid.toString()},
                     date:this.addformgroup.value.date}
                     console.log(data)
                  
                    this.bookingservice.createbooking(data).subscribe((response :any )=>{ 
                      // console.log(response);
                      this.data=response;
                      this.responsemessage=response?.message;
                    
                      this.notification.success('Success',this.responsemessage,{nzDuration:5000});
                  
                      this.router.navigateByUrl("/dashboard/confirmbookings")
                  
                    },(error)=>{
                      if(error.error?.message){
                        this.responsemessage= error.error?.message;
                      }
                      else{
                        this.responsemessage="somethingwentwrong";
                      }
                      this.notification.error('error',this.responsemessage,{nzDuration:5000});
                    })


     
                    
    }

    submitupdateForm(): void {
      console.log("from modal");
    }


    isVisibleMiddle = false;
 
 
  showModalMiddle(data:any){
    this.isVisibleMiddle = true;
  }

   


 
  handleOkMiddle(): void {
    console.log('click ok');
    this.isVisibleMiddle = false;
  }

  handleCancelMiddle(): void {
    this.isVisibleMiddle = false;
  }

}
