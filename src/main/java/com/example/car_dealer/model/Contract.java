package com.example.car_dealer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class Contract extends BaseModel {
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "depositId")
    private Deposit deposit;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "buyId")
    private Buy buy;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "sellId")
    private Sell sell;
}