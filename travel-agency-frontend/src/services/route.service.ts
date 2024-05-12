import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RouteService {
  constructor(private httpclient:HttpClient) { }

  getdrivers(){
    return this.httpclient.get("http://localhost:8081/route/get",{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
   }


   updateroute(data:any){
    return this.httpclient.post("http://localhost:8081/route/update",data,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
   }
   adddroute(data:any){
    return this.httpclient.post("http://localhost:8081/route/add",data,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
   }

   deleteroute(id:any){
    return this.httpclient.delete("http://localhost:8081/route/delete/"+id,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
   }
}
