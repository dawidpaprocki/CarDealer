package com.example.car_dealer.service;

import com.example.car_dealer.model.Car;
import com.example.car_dealer.model.Worker;
import com.example.car_dealer.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultCarService implements CarService {

    private final CarRepository carRepository;

    public DefaultCarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    private Boolean checkIfPresent(Car car){
        if (car.getId() != null) {
            Optional<Car> foundCar = carRepository.findById(car.getId());
            return foundCar.isPresent();
        }

        return false;
    }


    @Override
    public Optional<Car> getCarById(Long carId) {
       return carRepository.findById(carId);
    }

    @Override
    public Boolean addCarToDB(Car car) {

        if(!checkIfPresent(car)){
            carRepository.save(car);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean updateCarFromDB(Car car) {

        if(checkIfPresent(car)){
            carRepository.save(car);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean deleteCarFromDB(Car car) {

        if(checkIfPresent(car)){
            carRepository.delete(car);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Car> printNotSoldCars() {
        return  carRepository.findAllIsNotSold();

    }

    @Override
    public List<Car> printSoldCars() {
        return carRepository.findAllIsSold();
    }
}
