package com.inn.TravelAgency.Rest;


import com.inn.TravelAgency.POJO.Vehicle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@RequestMapping(path = "/vehicle")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface VehicleRest {

    @PostMapping(path = "/add")
    public ResponseEntity<String> addvehicle(@RequestBody(required = true) Map<String,String> requestmap);

    @PostMapping(path = "/update")
    public ResponseEntity<String> updatevehicle(@RequestBody(required = true)Map<String,String> requestmap);

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletevehicle(@PathVariable Integer id);

    @GetMapping(path = "/get")
    public ResponseEntity<List<Vehicle>> getallvehicles();

    @GetMapping(path = "/getunbooked")
    public ResponseEntity<List<Vehicle>> getvehiclebystatus();
}
