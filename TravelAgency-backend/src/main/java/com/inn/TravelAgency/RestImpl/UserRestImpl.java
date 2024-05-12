package com.inn.TravelAgency.RestImpl;

import com.inn.TravelAgency.Rest.UserRest;
import com.inn.TravelAgency.Service.UserService;
import com.inn.TravelAgency.Wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {


    @Autowired
    UserService userService;


    @Override
    public ResponseEntity<String> signup(Map<String, String> requestmap) {
        try{
            return userService.singup(requestmap);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestmap) {
        try{
            return userService.login(requestmap);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    public ResponseEntity<List<UserWrapper>> getAllusers() {
        try{
            return userService.getallusers();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }







    @Override
    public ResponseEntity<String> changepassword(Map<String, String> requestmap) {
        try{
        return userService.changepassword(requestmap);
        }
        catch (Exception ex){
        ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        }


