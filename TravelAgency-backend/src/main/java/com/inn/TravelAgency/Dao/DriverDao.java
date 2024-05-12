package com.inn.TravelAgency.Dao;

import com.inn.TravelAgency.POJO.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverDao extends JpaRepository<Driver,Integer> {

    List<Driver> findbystatus();
}
