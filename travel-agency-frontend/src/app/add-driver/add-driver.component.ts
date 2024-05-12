import { Component, OnInit } from '@angular/core';
import { FormBuilder, UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { DriverService } from 'src/services/driver.service';

@Component({
  selector: 'app-add-driver',
  templateUrl: './add-driver.component.html',
  styleUrls: ['./add-driver.component.scss']
})
export class AddDriverComponent   implements OnInit{

  constructor(private fb: UntypedFormBuilder,private modal:NzModalService,private fb1:FormBuilder,private driverservice:DriverService,private notification:NzNotificationService,private router:Router){}
  addformgroup!: UntypedFormGroup;
  responsemessage:any;

  submitAddform(){
    //  console.log (this.addformgroup.value)
    }

    ngOnInit(): void {
    
     
     this.addformgroup = this.fb.group({
       drivername: [null, [Validators.required]],
       mobilenumber: [null, [Validators.required]],
       licensenumber: [null, [Validators.required]],
     });
   
     
   
     }

     adddriver(){
      console.log(this.addformgroup.value);
      if (this.addformgroup.valid) {
        console.log('submit', this.addformgroup.value);
        var formdata= this.addformgroup.value;
   
        var data = {drivername:formdata.drivername,
                    licensenumber:formdata.licensenumber,
                      mobilenumber:formdata.mobilenumber,
                    }
                       console.log(data)
                       this.driverservice.adddriver(data).subscribe((response :any )=>{ 
                        // console.log(response);
                        data=response;
                        
                        this.responsemessage=response?.message;
                      
                        this.notification.success('Success',this.responsemessage,{nzDuration:5000});
                    
                        this.router.navigateByUrl("/dashboard/managedrivers");
                    
                      },(error)=>{
                        if(error.error?.message){
                          this.responsemessage= error.error?.message;
                        }
                        else{
                          this.responsemessage="somethingwentwrong";
                        }
                        this.notification.error('error',this.responsemessage,{nzDuration:5000});
                      })
  
  
       
      
      } else {
        Object.values(this.addformgroup.controls).forEach(control => {
          if (control.invalid) {
            control.markAsDirty();
            control.updateValueAndValidity({ onlySelf: true });
          }
        });
      }






     
     }
}
