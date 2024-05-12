import { Component, OnInit } from '@angular/core';
import { FormBuilder, UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { DriverService } from 'src/services/driver.service';
import { RouteService } from 'src/services/route.service';

@Component({
  selector: 'app-add-route',
  templateUrl: './add-route.component.html',
  styleUrls: ['./add-route.component.scss']
})
export class AddRouteComponent implements OnInit {
  constructor(private fb: UntypedFormBuilder,private modal:NzModalService,private fb1:FormBuilder,private routeservice:RouteService,private notification:NzNotificationService,private router:Router){}
  addformgroup!: UntypedFormGroup;
  responsemessage:any;

  submitAddform(){
    //  console.log (this.addformgroup.value)
    }
    ngOnInit(): void {
    
     
         
   this.addformgroup = this.fb.group({
    source: [null, [Validators.required]],
    destination: [null, [Validators.required]],
    distance: [null, [Validators.required]],
  });
    
      
    
      }



      addRoute(){
        console.log(this.addformgroup.value);
        if (this.addformgroup.valid) {
          console.log('submit', this.addformgroup.value);
          var formdata= this.addformgroup.value;
     
          var data = {to:formdata.destination,
                      from:formdata.source,
                      distance:formdata.distance,
                      }
                         console.log(data)
                         this.routeservice.adddroute(data).subscribe((response :any )=>{ 
                          // console.log(response);
                          data=response;
                          
                          this.responsemessage=response?.message;
                        
                          this.notification.success('Success',this.responsemessage,{nzDuration:5000});
                      
                          this.router.navigateByUrl("/dashboard/manageroutes");
                      
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
