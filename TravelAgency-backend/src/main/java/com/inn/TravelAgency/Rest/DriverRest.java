package com.inn.TravelAgency.Rest;


import com.inn.TravelAgency.POJO.Driver;
import com.inn.TravelAgency.POJO.Vehicle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/driver")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface DriverRest {


    @PostMapping(path = "/add")
    public ResponseEntity<String> adddriver(@RequestBody(required = true) Map<String,String> requestmap);

    @PostMapping(path = "/update")
    public ResponseEntity<String> updatedriver(@RequestBody(required = true)Map<String,String> requestmap);

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedriver(@PathVariable Integer id);


    @GetMapping(path = "/get")
    public ResponseEntity<List<Driver>> getalldrivers();

    @GetMapping(path = "/getavailable")
    public ResponseEntity<List<Driver>> getdriverbystatus();

}
