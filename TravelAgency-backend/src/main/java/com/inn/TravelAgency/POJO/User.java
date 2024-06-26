package com.inn.TravelAgency.POJO;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;




@NamedQuery(name = "User.getAllUsers", query = "select new com.inn.TravelAgency.Wrapper.UserWrapper(u.id,u.name,u.email,u.contactnumber) from User u where u.role='user'")
@NamedQuery(name = "User.findByEmailId",query ="select u from User u where u.email=:email")
@Entity
@DynamicInsert
@DynamicUpdate
@Data
@Table(name = "user")
public class User implements Serializable {

    private static long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "contactnumber")
    private String contactnumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

}
