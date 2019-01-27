package com.example.car_dealer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Car extends BaseModel {


    private Date date;
    private String model;
    private String nrOc;
    private Long nrRegister;
    private String fuelType;
    private Long mileage;
    private String engine;
    private Long power;
    private String gearBox;
    private String description;
    private Long testDriveAmount;
    private Long sold;
    private String owner;


}
