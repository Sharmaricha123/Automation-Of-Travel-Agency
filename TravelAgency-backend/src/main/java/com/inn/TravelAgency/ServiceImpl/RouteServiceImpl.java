package com.inn.TravelAgency.ServiceImpl;


import com.inn.TravelAgency.Dao.RouteDao;
import com.inn.TravelAgency.Jwt.JwtFilter;
import com.inn.TravelAgency.POJO.Routes;
import com.inn.TravelAgency.Service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {



    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    RouteDao routeDao;
    @Override
    public ResponseEntity<String> addroute(Map<String, String> requestmap) {
        try {
            if(jwtFilter.isAdmin()){

                if(this.Validateroutemap(requestmap)){
                    routeDao.save(this.getroutefromrequestmap(requestmap));
                    return new ResponseEntity<String>("{\"message\":\""+ "route added succesfully"+"\"}", HttpStatus.CREATED);
                }
                return new ResponseEntity<>("insufficient data ", HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>("unauthorized user ", HttpStatus.UNAUTHORIZED);
            }



        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong ", HttpStatus.BAD_REQUEST);
    }



    @Override
    public ResponseEntity<String> updateroute(Map<String, String> requestmap) {
        try {
            if(jwtFilter.isAdmin()){
//
                if(this.Validateroutemap(requestmap)){
//
                    Optional<Routes> routebydao= routeDao.findById(Integer.parseInt(requestmap.get("routeid")));
                    System.out.println(routebydao);

                    if(routebydao.isPresent()){
                        Routes route = this.getroutefromrequestmap(requestmap);

                        route.setRouteid(Integer.parseInt(requestmap.get("routeid")));
                        routeDao.save(route);
                        return new ResponseEntity<String>("{\"message\":\""+ "route updated succesfully"+"\"}", HttpStatus.OK);
                    }
                }
                return new ResponseEntity<>("insufficient data ", HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>("unauthorized user ", HttpStatus.UNAUTHORIZED);
            }



        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong ", HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<String> deleteroute(Integer id) {
        try {
            if(jwtFilter.isAdmin()){
                Optional<Routes> routebydao= routeDao.findById(id);
                if(routebydao.isPresent()){
                    routeDao.deleteById(id);
                    return new ResponseEntity<String>("{\"message\":\""+ "route deleted succesfully"+"\"}", HttpStatus.OK);
                }
                return new ResponseEntity<>("no such route", HttpStatus.BAD_REQUEST);

            }else {
                return new ResponseEntity<>("unauthorized user ", HttpStatus.UNAUTHORIZED);
            }



        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong ", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Routes>> getallroutes() {
        try {

            return new ResponseEntity<>(routeDao.findAll(),HttpStatus.OK);


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    private Routes getroutefromrequestmap(Map<String, String> requestmap) {
        Routes route =new Routes();
        route.setTo(requestmap.get("to"));
        route.setFrom(requestmap.get("from"));
        route.setDistance(requestmap.get("distance"));
        return route;
    }

    private boolean Validateroutemap(Map<String, String> requestmap) {

        if(requestmap.containsKey("to")&&requestmap.containsKey("from")&&requestmap.containsKey("distance")){
            return true;
        }
        return false;
    }

}

