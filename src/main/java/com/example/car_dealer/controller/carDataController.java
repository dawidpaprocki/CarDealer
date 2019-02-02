package com.example.car_dealer.controller;

import com.example.car_dealer.model.Car;
import com.example.car_dealer.service.CarService;
import com.example.car_dealer.service.PurchaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/boughtCars")
public class carDataController {

    private final CarService carService;
    private final PurchaseService purchaseService;

    public carDataController(CarService carService, PurchaseService purchaseService) {
        this.carService = carService;
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public String printAvailableCars(Model model) {
        List<Car> cars = carService.printNotSoldCars();
        cars.sort(Comparator.comparing(Car::getId));
        model.addAttribute("carList", cars);

        return "boughtCars";
    }

}
