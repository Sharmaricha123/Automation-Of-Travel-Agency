import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserStorageService } from '../Storage/user-storage.service';
import { Observable } from 'rxjs';
import { NzNotificationService } from 'ng-zorro-antd/notification';



@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})

export class NavbarComponent implements OnInit {
  responsemessage:any;

  isuserloggedin :boolean = UserStorageService.isloggedin();
  isadminloggedin:boolean = UserStorageService.isAdminloggedin();
  constructor(private router :Router,private notification:NzNotificationService){}
  ngOnInit(): void {
    this.isadminloggedin=this.isadminloggedin;
    this.router.events.subscribe(event=>{
      if(event.constructor.name==='NavigationEnd'){
       var ans = UserStorageService.isloggedin();
       console.log(ans)

        this.isuserloggedin= UserStorageService.isloggedin();
      }
    })

}
   logut(){
    this.notification.success('logged out successfully',this.responsemessage,{nzDuration:1000});
    window.localStorage.clear();
   }
}
