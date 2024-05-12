package com.inn.TravelAgency.RestImpl;

import com.inn.TravelAgency.POJO.Vehicle;
import com.inn.TravelAgency.Rest.VehicleRest;
import com.inn.TravelAgency.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class VehicleRestImpl implements VehicleRest {



     @Autowired
     VehicleService vehicleService;

    @Override
    public ResponseEntity<String> addvehicle(Map<String, String> requestmap) {
        try{
            return vehicleService.addvehicle(requestmap);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updatevehicle(Map<String, String> requestmap) {
        try{
            return vehicleService.updatevehicle(requestmap);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deletevehicle(Integer id) {
        try{
            return vehicleService.deletevehicle(id);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Vehicle>> getallvehicles() {
        try{
            return vehicleService.getallvehicles();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Vehicle>> getvehiclebystatus() {
        try{
            return vehicleService.getvehiclebystatus();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
