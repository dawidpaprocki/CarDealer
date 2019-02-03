package com.example.car_dealer.service;

import com.example.car_dealer.model.Buy;
import com.example.car_dealer.model.Car;
import com.example.car_dealer.model.Customer;
import com.example.car_dealer.model.Worker;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface PurchaseService {

    @Transactional
    Buy buyCar(Car car, Customer customer, BigDecimal price, Worker worker, Date date) ;
    List<Buy> carWaitingForAccept();
    Buy changeCarStatus(Long buyId, Long accept);

}
