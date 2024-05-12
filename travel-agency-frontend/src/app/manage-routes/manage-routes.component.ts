import { Component, OnInit } from '@angular/core';
import { FormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { NzNotificationService } from 'ng-zorro-antd/notification';
import { RouteService } from 'src/services/route.service';
import { UserStorageService } from '../Storage/user-storage.service';

@Component({
  selector: 'app-manage-routes',
  templateUrl: './manage-routes.component.html',
  styleUrls: ['./manage-routes.component.scss']
})
export class ManageRoutesComponent implements OnInit {
  constructor(private fb:FormBuilder,private userstorageservice:UserStorageService,private routeservice:RouteService,private notification:NzNotificationService,private router:Router){}
  responsemessage:any;
  validateForm!: UntypedFormGroup;
  datanew:any;
  isadminloggedin:boolean = UserStorageService.isAdminloggedin();

  data:any;
  ngOnInit(): void {
   this.getallusers();
     
   this.validateForm = this.fb.group({
    source: [null, [Validators.required]],
    destination: [null, [Validators.required]],
    distance: [null, [Validators.required]],
  });
  }
 getallusers(){
  this.routeservice.getdrivers().subscribe((response :any )=>{ 
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
    formdata.source=this.datanew.source;
   
    formdata.destination=this.datanew.destination;
    formdata.distance=this.datanew.distance;
    // console.log(formdata)
    console.log(this.datanew);
  }

   


 
  handleOkMiddle(): void {
    console.log('click ok');
    this.isVisibleMiddle = false;
  }

  handleCancelMiddle(): void {
    this.isVisibleMiddle = false;
  }

  submitupdateForm(): void {
    if (this.validateForm.valid) {
      console.log('submit', this.validateForm.value);
      var formdata= this.validateForm.value;
 
      var data = {to:formdata.destination,
                  from:formdata.source,
                    distance:formdata.distance,
                  routeid:this.datanew.routeid.toString()}
  console.log(data)
                    this.routeservice.updateroute(data).subscribe((response :any )=>{ 
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

  deleteroute(id:any){
    this.routeservice.deleteroute(id).subscribe((response :any )=>{ 
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

submitadd(){
  
  this.router.navigateByUrl("/dashboard/addroute")
}
}
