package com.example.car_dealer.service;

import com.example.car_dealer.model.Buy;
import com.example.car_dealer.model.Customer;
import com.example.car_dealer.model.Sell;
import com.example.car_dealer.model.Worker;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public interface    SellingService {


    @Transactional
    Sell sellCar(Long carId, Customer customer, BigDecimal price, Worker worker, Date date);
    List<Sell> carWaitingForAccept();

    Sell changeCarStatus(Long sellId, Long sellStatus);
    List<Sell> carAccepted();

}
