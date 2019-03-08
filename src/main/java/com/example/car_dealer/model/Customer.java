package com.example.car_dealer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class Customer extends BaseModel {
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
    @Size(min=1,max =150)
    private String nip;
    @NotNull
    @Size(min=1,max =150)
    private String pesel;

}
