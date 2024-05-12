package com.inn.TravelAgency.RestImpl;


import com.inn.TravelAgency.POJO.Booking;
import com.inn.TravelAgency.Rest.BookingRest;
import com.inn.TravelAgency.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingRestImpl implements BookingRest {


    @Autowired
    BookingService bookingService;
    @Override
    public ResponseEntity<String> create(Booking booking) {
        try{
            return bookingService.addbooking(booking);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> deletbooking(Integer id) {
        try{
            return bookingService.deletebooking(id);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<List<Booking>> getallbookings() {
        try{
            return bookingService.getallbookings();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Booking>> getbookingsofcurrentuser() {
        try{
            return bookingService.getbookingsofcurrentuser();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
