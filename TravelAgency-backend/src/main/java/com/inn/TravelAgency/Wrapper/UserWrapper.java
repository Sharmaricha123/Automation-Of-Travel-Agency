package com.inn.TravelAgency.Wrapper;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class UserWrapper {



    private Integer id;
    private String name;
    private String email;
    private String contactnumber;


    public UserWrapper(Integer id, String name, String email, String contactnumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactnumber = contactnumber;
    }
}
