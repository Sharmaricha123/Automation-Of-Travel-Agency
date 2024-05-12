import { Component, OnInit } from '@angular/core';
import { FormBuilder, UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { DriverService } from 'src/services/driver.service';
import { UserService } from 'src/services/user.service';
import { UserStorageService } from '../Storage/user-storage.service';

@Component({
  selector: 'app-manage-drivers',
  templateUrl: './manage-drivers.component.html',
  styleUrls: ['./manage-drivers.component.scss']
})
export class ManageDriversComponent  implements OnInit{
  constructor(private fb: UntypedFormBuilder,private userstorageservice:UserStorageService,private modal:NzModalService,private fb1:FormBuilder,private driverservice:DriverService,private notification:NzNotificationService,private router:Router){}
  responsemessage:any;
  validateForm!: UntypedFormGroup;
  addformgroup!: UntypedFormGroup;
  data:any;
  datanew:any;
  status:boolean=false;
  isadminloggedin:boolean = UserStorageService.isAdminloggedin();

  ngOnInit(): void {
   this.getallusers();
   
   this.validateForm = this.fb.group({
    drivername: [null, [Validators.required]],
    mobilenumber: [null, [Validators.required]],
    licensenumber: [null, [Validators.required]],
  });
  this.addformgroup = this.fb.group({
    drivername: [null, [Validators.required]],
    mobilenumber: [null, [Validators.required]],
    licensenumber: [null, [Validators.required]],
  });


  }
 getallusers(){
  this.driverservice.getdrivers().subscribe((response :any )=>{ 
    // console.log(response);
    this.data=response;
  
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
}


  isVisibleMiddle = false;
   isvisibleadd=false;
 
  showModalMiddle(data:any){
    this.isVisibleMiddle = true;
    this.datanew=data;
    var formdata= this.validateForm.value;
    formdata.drivername=this.datanew.drivername;
   
    formdata.licensenumber=this.datanew.licensenumber;
    formdata.mobilenumber=this.datanew.mobilenumber;
    // console.log(formdata)
    console.log(this.datanew);
  }

   
  showModalAdd(){
    console.log("add form opened")
    //  this.isvisibleadd = true;
   
  }

 
  handleOkMiddle(): void {
    console.log('click ok');
    this.isVisibleMiddle = false;
  }

  handleCancelMiddle(): void {
    this.isVisibleMiddle = false;
  }

  handleOkadd(): void {
    this.isvisibleadd = false;
  }

  handleCancelAdd(): void {
    this.isvisibleadd = false;
  }

  submitupdateForm(): void {
    if (this.validateForm.valid) {
      console.log('submit', this.validateForm.value);
      var formdata= this.validateForm.value;
 
      var data = {drivername:formdata.drivername,
                  licensenumber:formdata.licensenumber,
                    mobilenumber:formdata.mobilenumber,
                  driverid:this.datanew.driverid.toString()}
  console.log(data)
                    this.driverservice.updatedriver(data).subscribe((response :any )=>{ 
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
    this.driverservice.deletedriver(id).subscribe((response :any )=>{ 
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
        this.responsemessage="this driver is assocuiated with an booking";
      }
      this.notification.error('error',this.responsemessage,{nzDuration:5000});
    })
  }

submitadd(){
  this.router.navigateByUrl("/dashboard/adddriver")
}

 
}
