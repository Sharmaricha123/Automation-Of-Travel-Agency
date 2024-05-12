package com.inn.TravelAgency.Dao;

import com.inn.TravelAgency.POJO.User;

import com.inn.TravelAgency.Wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserDao extends JpaRepository<User,Integer> {

    User findByEmail(String email);


  User findByEmailId(@Param("email") String email);


    List<UserWrapper> getAllUsers();
}
