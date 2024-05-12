package com.inn.TravelAgency.Service;


import com.inn.TravelAgency.POJO.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {


    ResponseEntity<String> addbooking(Booking booking);

    ResponseEntity<String> deletebooking(Integer id);

    ResponseEntity<List<Booking>> getallbookings();

    ResponseEntity<List<Booking>> getbookingsofcurrentuser();
}
