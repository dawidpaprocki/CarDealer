package com.example.car_dealer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Buy extends BaseModel {
    private Date date;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "userId")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "workerId")
    private Worker worker;
    private Long acceptStatus;

}
