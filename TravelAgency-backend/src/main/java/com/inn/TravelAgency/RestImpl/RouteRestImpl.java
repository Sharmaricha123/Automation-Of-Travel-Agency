package com.inn.TravelAgency.RestImpl;

import com.inn.TravelAgency.POJO.Routes;
import com.inn.TravelAgency.Rest.RouteRest;
import com.inn.TravelAgency.Service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class RouteRestImpl implements RouteRest {



    @Autowired
    RouteService routeService;


    @Override
    public ResponseEntity<String> addroute(Map<String, String> requestmap) {
        try{
            return routeService.addroute(requestmap);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @Override
    public ResponseEntity<String> updateroute(Map<String, String> requestmap) {

        try{
            return routeService.updateroute(requestmap);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteroute(Integer id) {

        try{
            return routeService.deleteroute(id);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Routes>> getallroutes() {
        try{
            return routeService.getallroutes();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
