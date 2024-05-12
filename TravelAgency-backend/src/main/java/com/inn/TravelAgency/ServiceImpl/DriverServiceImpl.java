package com.inn.TravelAgency.ServiceImpl;

import com.inn.TravelAgency.Dao.DriverDao;
import com.inn.TravelAgency.Jwt.JwtFilter;
import com.inn.TravelAgency.POJO.Driver;
import com.inn.TravelAgency.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverDao driverDao;
    @Autowired
    JwtFilter jwtFilter;
    @Override
    public ResponseEntity<String> adddriver(Map<String, String> requestmap) {
        try {
            if(jwtFilter.isAdmin()){
                if(this.Validatedrivermap(requestmap)){
                    driverDao.save(this.getdriverfromrequestmap(requestmap));
                    return new ResponseEntity<String>("{\"message\":\""+ "driver added succesfully"+"\"}", HttpStatus.CREATED);

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
    public ResponseEntity<String> updatedriver(Map<String, String> requestmap) {
        try {
            if(jwtFilter.isAdmin()){
//
                if(this.Validatedrivermap(requestmap)){
                    System.out.println(requestmap);
                    Optional<Driver> vehiclebydao= driverDao.findById(Integer.parseInt(requestmap.get("driverid")));
                    System.out.println(vehiclebydao+"updatedriver");

                    if(vehiclebydao.isPresent()){
                        System.out.println("isprsent");
                        Driver newdriver= this.getdriverfromrequestmap(requestmap);
                        newdriver.setDriverid(Integer.parseInt(requestmap.get("driverid")));
                        newdriver.setStatus(vehiclebydao.get().getStatus());
                        driverDao.save(newdriver);
                        return new ResponseEntity<String>("{\"message\":\""+ "driver updated succesfully"+"\"}", HttpStatus.OK);
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
    public ResponseEntity<String> deletedriver(Integer id) {
        try {
            if(jwtFilter.isAdmin()){
                Optional<Driver> driverbydao= driverDao.findById(id);
                if(driverbydao.isPresent()){
                    driverDao.deleteById(id);
                    return new ResponseEntity<String>("{\"message\":\""+ "driver deleted succesfully"+"\"}", HttpStatus.OK);
                }
                return new ResponseEntity<>("no such vehicle", HttpStatus.BAD_REQUEST);

            }else {
                return new ResponseEntity<>("unauthorized user ", HttpStatus.UNAUTHORIZED);
            }



        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong ", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Driver>> getalldriver() {
        try {

            return new ResponseEntity<>(driverDao.findAll(),HttpStatus.OK);


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Driver>> getdriverbystatus() {
        try {

            return new ResponseEntity<>(driverDao.findbystatus(),HttpStatus.OK);


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }




    private Driver getdriverfromrequestmap(Map<String, String> requestmap){
        Driver driver = new Driver();
        driver.setDrivername(requestmap.get("drivername"));
        driver.setLicensenumber(requestmap.get("licensenumber"));
        driver.setMobilenumber(requestmap.get("mobilenumber"));
        driver.setStatus("available");
        return driver;
    }


    private  boolean Validatedrivermap(Map<String, String> requestmap){
        if(requestmap.containsKey("drivername")&&requestmap.containsKey("licensenumber")&&requestmap.containsKey("mobilenumber")){
            return true;
        }
        return false;
    }
}
