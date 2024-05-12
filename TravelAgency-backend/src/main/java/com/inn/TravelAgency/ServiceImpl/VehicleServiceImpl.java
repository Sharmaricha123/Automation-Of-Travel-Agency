package com.inn.TravelAgency.ServiceImpl;

import com.inn.TravelAgency.Dao.VehicleDao;
import com.inn.TravelAgency.Jwt.JwtFilter;
import com.inn.TravelAgency.POJO.Vehicle;
import com.inn.TravelAgency.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    VehicleDao vehicleDao;



    @Override
    public ResponseEntity<String> addvehicle(Map<String, String> requestmap) {
     try {
         if(jwtFilter.isAdmin()){
             if(this.Validatevehiclemap(requestmap)){
                 vehicleDao.save(this.getvehiclefromrequestmap(requestmap));
                 return new ResponseEntity<String>("{\"message\":\""+ "vehicle added succesfully"+"\"}", HttpStatus.CREATED);
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
    public ResponseEntity<String> updatevehicle(Map<String, String> requestmap) {
        try {
            if(jwtFilter.isAdmin()){
//
                if(this.Validatevehiclemap(requestmap)){
//
                   Optional<Vehicle> vehiclebydao= vehicleDao.findById(Integer.parseInt(requestmap.get("vehicleid")));

                   if(vehiclebydao.isPresent()){
                       System.out.println("isprsent");
                       Vehicle newVehicle= this.getvehiclefromrequestmap(requestmap);
                       newVehicle.setStatus(vehiclebydao.get().getStatus());
                       newVehicle.setVehicleid(Integer.parseInt(requestmap.get("vehicleid")));
                       vehicleDao.save(newVehicle);
                       return new ResponseEntity<String>("{\"message\":\""+ "vehicle updated succesfully"+"\"}", HttpStatus.OK);
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
    public ResponseEntity<String> deletevehicle(Integer id) {
        try {
            if(jwtFilter.isAdmin()){
                Optional<Vehicle> vehiclebydao= vehicleDao.findById(id);
                if(vehiclebydao.isPresent()){
                    vehicleDao.deleteById(id);
                    return new ResponseEntity<String>("{\"message\":\""+ "vehicle deleted succesfully"+"\"}", HttpStatus.OK);
                }
                return new ResponseEntity<>("no susch vehicle", HttpStatus.BAD_REQUEST);

            }else {
                return new ResponseEntity<>("unauthorized user ", HttpStatus.UNAUTHORIZED);
            }



        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong ", HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<List<Vehicle>> getallvehicles() {
        try {

            return new ResponseEntity<>(vehicleDao.findAll(),HttpStatus.OK);


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<List<Vehicle>> getvehiclebystatus() {
        try {

            return new ResponseEntity<>(vehicleDao.findbystatus(),HttpStatus.OK);


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }




    private Vehicle getvehiclefromrequestmap(Map<String, String> requestmap){
      Vehicle vehicle = new Vehicle();
      vehicle.setVehicalname(requestmap.get("vehiclename"));
      vehicle.setVehicaltype(requestmap.get("vehicletype"));
      vehicle.setVehicalnumber(requestmap.get("vehiclenumber"));
      vehicle.setPrice(requestmap.get("price"));
      vehicle.setStatus("unbooked");
      return vehicle;
    }


    private  boolean Validatevehiclemap(Map<String, String> requestmap){
        if(requestmap.containsKey("vehiclename")&&requestmap.containsKey("vehicletype")&&requestmap.containsKey("vehiclenumber")){
            return true;
        }
        return false;
    }

}
