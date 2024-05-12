package com.inn.TravelAgency.Dao;

import com.inn.TravelAgency.POJO.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingDao extends JpaRepository<Booking,Integer> {
    List<Booking> getBookingByUserId(Integer id);
}
