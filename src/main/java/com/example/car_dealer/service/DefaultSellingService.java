package com.example.car_dealer.service;

import com.example.car_dealer.model.*;
import com.example.car_dealer.repository.CarRepository;
import com.example.car_dealer.repository.CustomerRepository;
import com.example.car_dealer.repository.SellRepository;
import com.example.car_dealer.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class DefaultSellingService  implements SellingService {

    private final CustomerRepository customerRepository;
    private final WorkerRepository workerRepository;
    private final CarRepository carRepository;
    private final SellRepository sellRepository;

    public DefaultSellingService(CustomerRepository customerRepository, WorkerRepository workerRepository, CarRepository carRepository, SellRepository sellRepository) {
        this.customerRepository = customerRepository;
        this.workerRepository = workerRepository;
        this.carRepository = carRepository;
        this.sellRepository = sellRepository;
    }

    @Override
    public Sell sellCar(Long carId, Customer customer, BigDecimal price, Worker worker, Date date) {
        Sell sellCar = new Sell();
        Car car = carRepository.findById(carId).get();
        sellCar.setAmount(price);
        sellCar.setCar(car);
        sellCar.setDate(date);
        sellCar.setCustomer(customer);
        sellCar.setWorker(worker);
        car.setSold(1L);
        carRepository.save(car);
        return  sellRepository.save(sellCar);
    }

    @Override
    public Sell carStatus(Long sellId, Long sellStatus) {
        Sell carForSell = sellRepository.findById(sellId).get();
        Long carId = carForSell.getCar().getId();

        if (sellStatus>0){
            carForSell.setAcceptStatus(sellStatus);
        }else{
            Car car = carRepository.findById(carId).get();
            car.setSold(0L);
            carRepository.save(car);
        }
        return sellRepository.save(carForSell);
    }

}
