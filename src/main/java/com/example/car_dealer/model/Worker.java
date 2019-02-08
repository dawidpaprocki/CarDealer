package com.example.car_dealer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Worker  extends BaseModel{

    private String name;
    private String surName;
    private String address;
    private Date hiredDate;
    private String role;

}
