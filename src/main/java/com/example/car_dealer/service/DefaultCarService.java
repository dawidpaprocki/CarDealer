package com.example.car_dealer.service;

import com.example.car_dealer.model.Car;
import com.example.car_dealer.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultCarService implements CarService {

    private final CarRepository carRepository;

    public DefaultCarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car addCarToDB(Car car) {
          return   carRepository.save(car);
    }
}
