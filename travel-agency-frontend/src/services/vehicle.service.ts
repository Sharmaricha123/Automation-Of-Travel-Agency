import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  constructor(private httpclient:HttpClient) { }

  getvehicles(){
    return this.httpclient.get("http://localhost:8081/vehicle/get",{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
   }
   getvehiclebystatus(){
    return this.httpclient.get("http://localhost:8081/vehicle/getunbooked",{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
   }

   addvehicle(data:any){
    return this.httpclient.post("http://localhost:8081/vehicle/add",data,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
   }
   updatevehicle(data:any){
    return this.httpclient.post("http://localhost:8081/vehicle/update",data,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
   }

   deletevehicle(id:any){
    return this.httpclient.delete("http://localhost:8081/vehicle/delete/"+id,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
   }

}
