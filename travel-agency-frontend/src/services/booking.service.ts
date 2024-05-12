import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(private httpclient:HttpClient) { }

  getbookings(){
    return this.httpclient.get("http://localhost:8081/booking/get",{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })  
   }
   
   getuserbookings(){
    return this.httpclient.get("http://localhost:8081/booking/getuserbooking",{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })  
   }
   
  createbooking(data:any){
    return this.httpclient.post("http://localhost:8081/booking/create",data,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })  
   }
   
   deletebooking(id:any){
    return this.httpclient.delete("http://localhost:8081/booking/delete/"+id,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
   }
}
