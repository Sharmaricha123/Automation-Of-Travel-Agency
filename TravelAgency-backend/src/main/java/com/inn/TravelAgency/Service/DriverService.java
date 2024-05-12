package com.inn.TravelAgency.Service;


import com.inn.TravelAgency.POJO.Driver;
import com.inn.TravelAgency.POJO.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DriverService {
    ResponseEntity<String> adddriver(Map<String, String> requestmap);

    ResponseEntity<String> updatedriver(Map<String, String> requestmap);

    ResponseEntity<String> deletedriver(Integer id);

    ResponseEntity<List<Driver>> getalldriver();

    ResponseEntity<List<Driver>> getdriverbystatus();
}
