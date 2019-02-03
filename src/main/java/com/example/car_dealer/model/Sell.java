package com.example.car_dealer.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Sell extends BaseModel {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "workerId")
    private Worker worker;
    private Long AcceptStatus;
}
