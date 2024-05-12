package com.inn.TravelAgency.Service;

import com.inn.TravelAgency.POJO.Vehicle;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface VehicleService {
    ResponseEntity<String> addvehicle(Map<String, String> requestmap);

    ResponseEntity<String> updatevehicle(Map<String, String> requestmap);

    ResponseEntity<String> deletevehicle(Integer id);

    ResponseEntity<List<Vehicle>> getallvehicles();

    ResponseEntity<List<Vehicle>> getvehiclebystatus();
}
