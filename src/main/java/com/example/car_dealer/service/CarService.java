package com.example.car_dealer.service;

import com.example.car_dealer.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Optional<Car> getCarById(Long carId);
    Boolean addCarToDB(Car car);
    Boolean updateCarFromDB(Car car);
    Boolean deleteCarFromDB(Car car);
    List<Car> printNotSoldCars();
    List<Car> printSoldCars();
}
