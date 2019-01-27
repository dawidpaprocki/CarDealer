package com.example.car_dealer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Deposit  extends  BaseModel{
    private Date date;
    @ManyToOne
    @JoinColumn(name = "userId")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;

}
