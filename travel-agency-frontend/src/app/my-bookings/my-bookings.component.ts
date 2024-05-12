
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { BookingService } from 'src/services/booking.service';

@Component({
  selector: 'app-my-bookings',
  templateUrl: './my-bookings.component.html',
  styleUrls: ['./my-bookings.component.scss']
})
export class MyBookingsComponent implements OnInit{
  constructor(private fb:FormBuilder,private bookingservice:BookingService,private notification:NzNotificationService,private router:Router){}
  responsemessage:any;

  data:any;
  ngOnInit(): void {
   this.getallusers();
  }
 getallusers(){
  this.bookingservice.getuserbookings().subscribe((response :any )=>{ 
    console.log(response);
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

deletebooking(id:any){
  this.bookingservice.deletebooking(id).subscribe((response :any )=>{ 
    // console.log(response);
    this.data=response;
    this.getallusers();
    this.responsemessage=response?.message;
    this.notification.success('Success',this.responsemessage,{nzDuration:5000});

    

  },(error)=>{
    if(error.error?.message){
      this.responsemessage= error.error?.message;
    }
    else{
      this.responsemessage="this Route is assocuiated with an booking";
    }
    this.notification.error('error',this.responsemessage,{nzDuration:5000});
  })
}

}
