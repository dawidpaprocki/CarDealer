package com.example.car_dealer.dtos;

import com.example.car_dealer.model.BaseModel;
import com.example.car_dealer.model.Car;
import com.example.car_dealer.model.Customer;
import com.example.car_dealer.model.Worker;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class SellDto extends BaseModel {
    @NotNull
    private String date;
    @NotNull
    @Min(value = 1000)
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
