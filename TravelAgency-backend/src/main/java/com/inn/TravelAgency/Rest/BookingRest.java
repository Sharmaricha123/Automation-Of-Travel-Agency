package com.inn.TravelAgency.Rest;

import com.inn.TravelAgency.POJO.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/booking")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface BookingRest{


    @PostMapping(path = "/create")
    public ResponseEntity<String> create(@RequestBody(required = true)Booking booking);

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deletbooking(@PathVariable Integer id);

    @GetMapping(path = "/get")
    public ResponseEntity<List<Booking>> getallbookings();

    @GetMapping(path = "/getuserbooking")
    public ResponseEntity<List<Booking>> getbookingsofcurrentuser();

}
