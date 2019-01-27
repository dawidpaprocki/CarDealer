package com.example.car_dealer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class Contract extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "depositId")
    private Deposit deposit;
    @ManyToOne
    @JoinColumn(name = "buyId")
    private Buy buy;
    @ManyToOne
    @JoinColumn(name = "sellId")
    private Sell sell;

}
