package com.inn.TravelAgency.Service;

import com.inn.TravelAgency.Wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    ResponseEntity<String> singup(Map<String, String> requestmap);

    ResponseEntity<String> login(Map<String, String> requestmap);


    ResponseEntity<List<UserWrapper>> getallusers();


    ResponseEntity<String> changepassword(Map<String, String> requestmap);

}
