package com.example.car_dealer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Customer extends BaseModel {
    private String name;
    private String surName;
    private String address;
    private String nip;
    private String pesel;

}
