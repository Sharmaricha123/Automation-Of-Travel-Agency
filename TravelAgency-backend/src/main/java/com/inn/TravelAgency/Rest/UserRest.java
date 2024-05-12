package com.inn.TravelAgency.Rest;


import com.inn.TravelAgency.Wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface UserRest {

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signup(@RequestBody(required = true)Map<String,String> requestmap);

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true)Map<String,String> requestmap);

    @GetMapping(path = "/getallusers")
    public ResponseEntity<List<UserWrapper>> getAllusers();

     @PostMapping("/changepassword")
    public ResponseEntity<String> changepassword(@RequestBody(required = true) Map<String,String> requestmap);

}
