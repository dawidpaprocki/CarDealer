package com.example.car_dealer.dtos;

import lombok.Data;

import javax.validation.constraints.*;
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
    @Min(value = 1)
    @Max(value = 10)
    private Long nrRegister;
    @NotNull
    @Size(min=1,max =150)
    private String fuelType;
    @NotNull
    @Min(value = 1)
    @Max(value = 10)
    private Long mileage;
    @NotNull
    @Size(min=1,max =150)
    private String engine;
    @NotNull
    @Min(value = 1)
    @Max(value = 10)
    private Long power;
    @NotNull
    @Size(min=1,max =150)
    private String gearBox;
    @NotNull
    @Size(min=1,max =150)
    private String description;
    private Long testDriveAmount;
    private Long sold;
    private String owner;
}
