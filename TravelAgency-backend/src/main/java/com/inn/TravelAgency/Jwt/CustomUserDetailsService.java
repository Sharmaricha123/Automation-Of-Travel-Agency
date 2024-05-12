package com.inn.TravelAgency.Jwt;


import com.inn.TravelAgency.Dao.UserDao;
import com.inn.TravelAgency.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    private com.inn.TravelAgency.POJO.User userdetails;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userdetails = userDao.findByEmailId(username);
        if (!Objects.isNull(userdetails)) {
            return new org.springframework.security.core.userdetails.User(userdetails.getEmail(), userdetails.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("user not found");
        }
    }

    public User getUserdetails(){return userdetails;}

}