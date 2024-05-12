package com.inn.TravelAgency.ServiceImpl;

import com.inn.TravelAgency.Dao.UserDao;
import com.inn.TravelAgency.Jwt.CustomUserDetailsService;
import com.inn.TravelAgency.Jwt.JwtFilter;
import com.inn.TravelAgency.Jwt.JwtUtil;
import com.inn.TravelAgency.POJO.User;
import com.inn.TravelAgency.Service.UserService;
import com.inn.TravelAgency.Wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ServiceImpl implements UserService {


 @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    UserDao userDao;
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public ResponseEntity<String> singup(Map<String, String> requestmap) {

       try{
           if(Validatesignupmap(requestmap)){
               User user= userDao.findByEmail(requestmap.get("email"));

               if(Objects.isNull(user)){
                   User user1 = getuserfromrequestmap(requestmap);
                   userDao.save(user1);
                   return new ResponseEntity<String>("{\"message\":\""+ "user registerd sucesfully"+"\"}", HttpStatus.CREATED);
               }
               else{
                   return new ResponseEntity<String>("user with same email already exist", HttpStatus.BAD_REQUEST);
               }
           }else{
               return new ResponseEntity<String>("insufficent data ", HttpStatus.BAD_REQUEST);
           }

       }catch (Exception ex){
           ex.printStackTrace();
       }
       return new ResponseEntity<>("something went wrong ",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestmap) {
    try{

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestmap.get("email"),requestmap.get("password")));
        if(auth.isAuthenticated()){

            return new ResponseEntity<String>("{\"token\":\""+jwtUtil.generateToken(customUserDetailsService.getUserdetails().getEmail(),
                                                     customUserDetailsService.getUserdetails().getRole())+"\"}",HttpStatus.OK);
        }
    }catch (Exception ex){
        ex.printStackTrace();
    }
     return new ResponseEntity<String>("Bad credentials",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getallusers() {


        try {
            if(jwtFilter.isAdmin()){

                return new ResponseEntity<>(userDao.getAllUsers(),HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new ArrayList<>(),HttpStatus.UNAUTHORIZED);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }



    @Override
    public ResponseEntity<String> changepassword(Map<String, String> requestmap) {
       try{
           User userobj= userDao.findByEmail(jwtFilter.getcurrentuser());
           if(!Objects.isNull(userobj)){
               if(userobj.getPassword().equals(requestmap.get("oldpassword"))){
                   userobj.setPassword(requestmap.get("newpassword"));
                   System.out.println(userobj);
                   userDao.save(userobj);

                   return new ResponseEntity<String>("{\"message\":\""+ "password changed succesfully"+"\"}", HttpStatus.OK);
               }
               return new ResponseEntity<String>("{\"message\":\""+ "old pass wrong"+"\"}", HttpStatus.BAD_REQUEST);
           }
           return new ResponseEntity<>("something went wrong",HttpStatus.BAD_REQUEST);
       }
       catch (Exception ex){
           ex.printStackTrace();
       }
        return new ResponseEntity<>("something went wrong",HttpStatus.BAD_REQUEST);
    }



    private  boolean Validatesignupmap(Map<String, String> requestmap){
        if(requestmap.containsKey("name")&&requestmap.containsKey("contactnumber")&&requestmap.containsKey("email")&&requestmap.containsKey("password")){
            return true;
        }
        return false;
    }


    private User getuserfromrequestmap(Map<String, String> requestmap){
        User user = new User();
        user.setName(requestmap.get("name"));
        user.setContactnumber(requestmap.get("contactnumber"));
        user.setEmail(requestmap.get("email"));
        user.setPassword(requestmap.get("password"));
        user.setRole("user");
        return user;
    }
}
