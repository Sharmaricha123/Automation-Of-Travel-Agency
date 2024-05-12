import { Component, OnInit } from '@angular/core';
import { FormBuilder, UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { VehicleService } from 'src/services/vehicle.service';

@Component({
  selector: 'app-add-vehicle',
  templateUrl: './add-vehicle.component.html',
  styleUrls: ['./add-vehicle.component.scss']
})
export class AddVehicleComponent implements OnInit{

  constructor(private fb: UntypedFormBuilder,private modal:NzModalService,private fb1:FormBuilder,private vehicleservice:VehicleService,private notification:NzNotificationService,private router:Router){}
  addformgroup!: UntypedFormGroup;
  responsemessage:any;
  submitAddform(){
    //  console.log (this.addformgroup.value)
    }


    ngOnInit(): void {
    
     
      this.addformgroup = this.fb.group({
        vehiclenumber: [null, [Validators.required]],
        vehicletype: [null, [Validators.required]],
        vehiclename: [null, [Validators.required]],
        price: [null, [Validators.required]],
      });
    
      
    
      }
      addvehicle(){
        console.log(this.addformgroup.value);
        if (this.addformgroup.valid) {
          console.log('submit', this.addformgroup.value);
          var formdata= this.addformgroup.value;
     
          var data = {vehiclename:formdata.vehiclename,
                       vehicletype:formdata.vehicletype,
                        vehiclenumber:formdata.vehiclenumber,
                         price:formdata.price,
                      }
                         console.log(data)
                         this.vehicleservice.addvehicle(data).subscribe((response :any )=>{ 
                          // console.log(response);
                          data=response;
                          
                          this.responsemessage=response?.message;
                        
                          this.notification.success('Success',this.responsemessage,{nzDuration:5000});
                      
                          this.router.navigateByUrl("/dashboard/managevehicles");
                      
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
