package com.example.car_dealer.dtos;

import com.example.car_dealer.model.Car;
import com.example.car_dealer.model.Customer;
import com.example.car_dealer.model.Worker;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class BuyDto {
    private String date;
    private BigDecimal amount;
    private Customer customer;
    private Car car;
    private Worker worker;
    private Long AcceptStatus;
}
