package com.example.car_dealer.dtos;

import com.example.car_dealer.model.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class WorkerDto extends BaseModel {
    private String name;
    private String surName;
    private String address;
    private String hiredDate;
}
