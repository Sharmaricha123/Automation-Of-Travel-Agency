package com.inn.TravelAgency.ServiceImpl;


import com.inn.TravelAgency.Dao.*;
import com.inn.TravelAgency.Jwt.JwtFilter;
import com.inn.TravelAgency.POJO.*;
import com.inn.TravelAgency.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingDao bookingDao;

 @Autowired
    DriverDao driverDao;
    @Autowired
    VehicleDao vehicleDao;
    @Autowired
    RouteDao routeDao;
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    UserDao userDao;
    @Override
    public ResponseEntity<String> addbooking(Booking booking) {
        try {
            System.out.println(booking);
            User user = userDao.findByEmail(jwtFilter.getcurrentuser());
            Vehicle vehicle= vehicleDao.getById(booking.getVehicle().getVehicleid());
            vehicle.setStatus("booked");
            Routes routes= routeDao.getById(booking.getRoute().getRouteid());
            List<Driver> availabledrivers=driverDao.findbystatus();
            System.out.println(availabledrivers);
            if(availabledrivers.isEmpty()){
                return new ResponseEntity<>("no driver",HttpStatus.BAD_REQUEST);
            }
            Random randomizer = new Random();
            Driver randomdriver = availabledrivers.get(randomizer.nextInt(availabledrivers.size()));

            randomdriver.setStatus("unavailable");

            booking.setUser(user);
            booking.setDriver(randomdriver);
            booking.setRoute(routes);
            booking.setVehicle(vehicle);
            Integer finalprice = Integer.parseInt(vehicle.getPrice())*Integer.parseInt(routes.getDistance());
            booking.setPrice(Integer.toString(finalprice));
            bookingDao.save(booking);

            return new ResponseEntity<String>("{\"message\":\""+ "Booking succesfull"+"\"}", HttpStatus.CREATED);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("somethingwentwrong", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deletebooking(Integer id) {
        try {

                Optional<Booking> bookingbydao= bookingDao.findById(id);
                if(bookingbydao.isPresent()){
                    Booking booking = bookingDao.getById(id);
                    Vehicle vehicle= vehicleDao.getById(booking.getVehicle().getVehicleid());
                    vehicle.setStatus("unbooked");
                    vehicleDao.save(vehicle);
                    Driver driver= driverDao.getById(booking.getDriver().getDriverid());
                    driver.setStatus("available");
                    driverDao.save(driver);
                    bookingDao.deleteById(id);
                    return new ResponseEntity<String>("{\"message\":\""+ "rbooking deleted succesfully"+"\"}", HttpStatus.OK);
                }
                return new ResponseEntity<>("no such booking ", HttpStatus.BAD_REQUEST);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong ", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Booking>> getallbookings() {
        try {


            return new ResponseEntity<>(bookingDao.findAll(),HttpStatus.OK);


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Booking>> getbookingsofcurrentuser() {
        try {
            User user = userDao.findByEmail(jwtFilter.getcurrentuser());
            return new ResponseEntity<>(bookingDao.getBookingByUserId(user.getId()),HttpStatus.OK);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}


