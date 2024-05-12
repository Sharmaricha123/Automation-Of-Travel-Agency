package com.inn.TravelAgency.Service;


import com.inn.TravelAgency.POJO.Driver;

import com.inn.TravelAgency.POJO.Routes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RouteService {
    ResponseEntity<String> addroute(Map<String, String> requestmap);

    ResponseEntity<String> updateroute(Map<String, String> requestmap);

    ResponseEntity<String> deleteroute(Integer id);

    ResponseEntity<List<Routes>> getallroutes();
}
