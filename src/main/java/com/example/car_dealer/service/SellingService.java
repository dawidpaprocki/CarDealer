package com.example.car_dealer.service;

import com.example.car_dealer.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;


public interface    SellingService {


    @Transactional
    Sell sellCar(Long carId, Customer customer, BigDecimal price, Worker worker, Date date);


    Sell carStatus(Long sellId, Long accept);

}
