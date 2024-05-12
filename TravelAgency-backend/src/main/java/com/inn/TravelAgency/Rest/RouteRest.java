package com.inn.TravelAgency.Rest;


import com.inn.TravelAgency.POJO.Driver;
import com.inn.TravelAgency.POJO.Routes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/route")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface RouteRest {



    @PostMapping(path = "/add")
    public ResponseEntity<String> addroute(@RequestBody(required = true) Map<String,String> requestmap);

    @PostMapping(path = "/update")
    public ResponseEntity<String> updateroute(@RequestBody(required = true)Map<String,String> requestmap);

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteroute(@PathVariable Integer id);


    @GetMapping(path = "/get")
    public ResponseEntity<List<Routes>> getallroutes();

}
