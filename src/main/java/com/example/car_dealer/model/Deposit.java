package com.example.car_dealer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Deposit  extends  BaseModel{
    private Date date;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "userId")
    private Customer customer;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "carId")
    private Car car;

}
