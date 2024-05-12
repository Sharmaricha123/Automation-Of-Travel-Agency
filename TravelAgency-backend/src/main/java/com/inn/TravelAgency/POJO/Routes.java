package com.inn.TravelAgency.POJO;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@DynamicUpdate
@DynamicInsert
@Entity
@Data
@Table(name = "routes")
public class Routes implements Serializable {
    private static long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer routeid;

    @Column(name = "to")
    private String to;

    @Column(name = "from")
    private String from;


    @Column(name = "distance")
    private String distance;


}
