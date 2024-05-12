package com.inn.TravelAgency.Dao;

import com.inn.TravelAgency.POJO.Vehicle;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.function.Function;

public interface VehicleDao extends JpaRepository<Vehicle,Integer> {


    List<Vehicle> findbystatus();
}
