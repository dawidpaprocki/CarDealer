package com.example.car_dealer.dtos;

import com.example.car_dealer.model.Car;
import com.example.car_dealer.model.Customer;
import com.example.car_dealer.model.Worker;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class BuyDto {
    @NotNull
    @Future
    private String date;
    @NotNull
    @Min(value = 1)
    private BigDecimal amount;
    @NotNull
    private Customer customer;
    @NotNull
    private Car car;
    @NotNull
    private Worker worker;
    private Long AcceptStatus;
}
