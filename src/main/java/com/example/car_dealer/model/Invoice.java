package com.example.car_dealer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class Invoice extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "contractId")
    private Contract contract;
    @ManyToOne
    @JoinColumn(name = "workerId")
    private Worker worker;

}
