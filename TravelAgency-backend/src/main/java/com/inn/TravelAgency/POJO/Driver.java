package com.inn.TravelAgency.POJO;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;


@NamedQuery(name = "Driver.findbystatus",query = "select d from Driver d where d.status='available'")
@Entity
@DynamicInsert
@DynamicUpdate
@Data
@Table(name = "driver")
public class Driver implements Serializable {
    private static long serialVersionUID= 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer driverid;

    @Column(name = "license")
    private String licensenumber;

    @Column(name = "name")
    private String drivername;

    @Column(name = "number")
    private String mobilenumber;

    @Column(name = "status")
    private String status;
}
