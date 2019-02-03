package com.example.car_dealer.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CarDto {
    private String date;
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
