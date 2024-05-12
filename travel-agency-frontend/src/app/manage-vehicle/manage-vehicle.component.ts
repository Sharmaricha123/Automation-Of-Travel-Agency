import { Component, OnInit } from '@angular/core';
import { FormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { VehicleService } from 'src/services/vehicle.service';
import { UserStorageService } from '../Storage/user-storage.service';

@Component({
  selector: 'app-manage-vehicle',
  templateUrl: './manage-vehicle.component.html',
  styleUrls: ['./manage-vehicle.component.scss']
})
export class ManageVehicleComponent implements OnInit{
  constructor(private fb:FormBuilder,private userstorageservice:UserStorageService,private vehicleservice:VehicleService,private notification:NzNotificationService,private router:Router){}
  responsemessage:any;
  validateForm!: UntypedFormGroup;
  datanew:any;
   data:any;
   isadminloggedin:boolean = UserStorageService.isAdminloggedin();
  ngOnInit(): void {
   this.getallusers();
   this.validateForm = this.fb.group({
    vehiclenumber: [null, [Validators.required]],
    vehicletype: [null, [Validators.required]],
    vehiclename: [null, [Validators.required]],
    price: [null, [Validators.required]],

  });
  }
 getallusers(){
  this.vehicleservice.getvehicles().subscribe((response :any )=>{ 
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
isVisibleMiddle = false;
 
  showModalMiddle(data:any){
    this.isVisibleMiddle = true;
    this.datanew=data;
    var formdata= this.validateForm.value;
    formdata.vehiclename=this.datanew.vehicalname;
   
    formdata.vehiclenumber=this.datanew.vehicalnumber;
    formdata.vehicletype=this.datanew.vehicaltype;
    formdata.price=this.datanew.price;
    // console.log(formdata)
    // console.log(this.datanew);
  }
  handleOkMiddle(): void {
    console.log('click ok');
    this.isVisibleMiddle = false;
  }

  handleCancelMiddle(): void {
    this.isVisibleMiddle = false;
  }
  handleCancelAdd(): void {
    this.isVisibleMiddle = false;
  }



  submitupdateForm(): void {
    if (this.validateForm.valid) {
      // console.log('submit', this.validateForm.value);
      var formdata= this.validateForm.value;
 
      var data = {vehiclename:formdata.vehiclename,
                  vehicletype:formdata.vehicletype,
                    vehiclenumber:formdata.vehiclenumber,
                    price:formdata.price,
                  vehicleid:this.datanew.vehicleid.toString()}
  // console.log(data)
                    this.vehicleservice.updatevehicle(data).subscribe((response :any )=>{ 
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
                        this.responsemessage="somethingwentwrong";
                      }
                      this.notification.error('error',this.responsemessage,{nzDuration:5000});
                    })


      this.isVisibleMiddle = false;
    
    } else {
      Object.values(this.validateForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }

    
  }



  deletedriver(id:any){
    this.vehicleservice.deletevehicle(id).subscribe((response :any )=>{ 
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
        this.responsemessage="this vehicle is assocuiated with an booking";
      }
      this.notification.error('error',this.responsemessage,{nzDuration:5000});
    })
  }

submitadd(){
  this.router.navigateByUrl("/dashboard/addvehicle")
}


}
