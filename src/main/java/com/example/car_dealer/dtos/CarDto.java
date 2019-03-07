package com.example.car_dealer.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class CarDto {
    @NotNull
    private String date;
    @NotNull
    @Size(min=1,max =150)
    private String model;
    @NotNull
    @Size(min=1,max =150)
    private String nrOc;
    @NotNull
    @Size(min=1,max =150)
    private Long nrRegister;
    @NotNull
    @Size(min=1,max =150)
    private String fuelType;
    @NotNull
    @Size(min=1,max =150)
    private Long mileage;
    @NotNull
    @Size(min=1,max =150)
    private String engine;
    @NotNull
    @Size(min=1,max =150)
    private Long power;
    @NotNull
    @Size(min=1,max =150)
    private String gearBox;
    @NotNull
    @Size(min=1,max =150)
    private String description;
    @NotNull
    @Size(min=1,max =150)
    private Long testDriveAmount;
    private Long sold;
    private String owner;
}
