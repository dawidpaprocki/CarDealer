package com.example.car_dealer.service;

import com.example.car_dealer.model.*;
import com.example.car_dealer.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class DefaultPurchaseService implements PurchaseService {

    private final CustomerRepository customerRepository;
    private final WorkerRepository workerRepository;
    private final CarRepository carRepository;
    private final BuyRepository buyRepository;

    public DefaultPurchaseService(CustomerRepository customerRepository, WorkerRepository workerRepository, CarRepository carRepository, BuyRepository buyRepository) {
        this.customerRepository = customerRepository;
        this.workerRepository = workerRepository;
        this.carRepository = carRepository;
        this.buyRepository = buyRepository;
    }

    @Override
    public Buy buyCar(Car car, Customer customer, BigDecimal price, Worker worker, Date date) {
        Buy buyCar = new Buy();
        buyCar.setAmount(price);
        buyCar.setCar(car);
        buyCar.setDate(date);
        buyCar.setCustomer(customer);
        buyCar.setWorker(worker);

        return buyRepository.save(buyCar);
    }

    @Override
    public List<Buy> carWaitingForAccept() {
        return buyRepository.findByAcceptStatus(0);
    }

    @Override
    public Buy changeCarStatus(Long buyId, Long buyStatus) {
        Buy carForBuy = buyRepository.findById(buyId).get();
        Long carId = carForBuy.getCar().getId();
        if (buyStatus>0){
            carForBuy.setAcceptStatus(buyStatus);
        }else{
            Car car = carRepository.findById(carId).get();
            car.setOwner("Car_Dealer");
            carRepository.save(car);
        }
        carForBuy.setAcceptStatus(buyStatus);

        return buyRepository.save(carForBuy);
    }


}
