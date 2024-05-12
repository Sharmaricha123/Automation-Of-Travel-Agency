package com.inn.TravelAgency.POJO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "Vehicle.findbystatus",query = "select v from Vehicle v where v.status='unbooked'")
@Entity
@DynamicInsert
@DynamicUpdate
@Data
@Table(name = "vehicle")
public class Vehicle implements Serializable {
    private static long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer vehicleid;
    @Column(name = "type")
    private String vehicaltype;
    @Column(name = "name")
    private String vehicalname;
    @Column(name = "number")
    private String vehicalnumber;

    @Column(name = "price")
    private String price;

    @Column(name = "status")
    private String status;

}
