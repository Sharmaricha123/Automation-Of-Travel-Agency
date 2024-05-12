import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DriverService {

  constructor(private httpclient:HttpClient) { }

  getdrivers(){
    return this.httpclient.get("http://localhost:8081/driver/get",{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
   }
   updatedriver(data:any){
    return this.httpclient.post("http://localhost:8081/driver/update",data,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
   }
   adddriver(data:any){
    return this.httpclient.post("http://localhost:8081/driver/add",data,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
   }

   deletedriver(id:any){
    return this.httpclient.delete("http://localhost:8081/driver/delete/"+id,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
   }
}
