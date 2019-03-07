package com.example.car_dealer.dtos;

import com.example.car_dealer.model.BaseModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class WorkerDto {
    @NotNull
    @Size(min=1,max =150)
    private String name;
    @NotNull
    @Size(min=1,max =150)
    private String surName;
    @NotNull
    @Size(min=1,max =150)
    private String address;
    @NotNull
    private String hiredDate;
}
