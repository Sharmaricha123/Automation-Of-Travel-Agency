import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserStorageService } from '../Storage/user-storage.service';

@Component({
  selector: 'app-confrimbooking',
  templateUrl: './confrimbooking.component.html',
  styleUrls: ['./confrimbooking.component.scss']
})
export class ConfrimbookingComponent implements OnInit {

  constructor(private router:Router,private user:UserStorageService){}
  isadminloggedin:boolean = UserStorageService.isAdminloggedin();
  ngOnInit(): void {
   
    setTimeout(() => {
      if(this.isadminloggedin){
      this.router.navigate(['dashboard/managebookings']);}
      else{
        this.router.navigate(['dashboard/mybookings']);}
      
  }, 2000);  //5s
  }
 
}
