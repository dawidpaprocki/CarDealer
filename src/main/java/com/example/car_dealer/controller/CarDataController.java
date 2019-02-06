package com.example.car_dealer.controller;

import com.example.car_dealer.model.Buy;
import com.example.car_dealer.model.Car;
import com.example.car_dealer.model.Sell;
import com.example.car_dealer.service.CarService;
import com.example.car_dealer.service.PurchaseService;
import com.example.car_dealer.service.SellingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cars")
public class CarDataController {

    private final CarService carService;
    private final SellingService sellingService;
    private final PurchaseService purchaseService;

    public CarDataController(CarService carService, PurchaseService purchaseService, SellingService sellingService) {
        this.carService = carService;
        this.purchaseService = purchaseService;
        this.sellingService = sellingService;
    }


    @GetMapping
    public String printAvailableCars(Model model) {
        List<Buy> boughtCars = purchaseService.carAccepted();
        List<Car> cars = boughtCars
                .stream()
                .map(Buy::getCar)
                .filter(car -> car.getSold() == 0)
                .collect(Collectors.toList());
        cars.sort(Comparator.comparing(Car::getId));
        model.addAttribute("carList", cars);

        return "cars";
    }


    @RequestMapping("/{id}")
    public String getCar(
            @PathVariable("id") Long carId,
            Model model) {
        Optional<Car> foundCar = carService.getCarById(carId);
        if (foundCar.isPresent()) {
            model.addAttribute("car", foundCar.get());
        }
        return "carDetails";
    }

    @RequestMapping("/waitingCarsBuy")
    public String waitingCarsToBuy(Model model) {
        List<Buy> buysToAccept = purchaseService.carWaitingForAccept();
        List<Car> waitingCarsList = buysToAccept.stream().map(Buy::getCar).collect(Collectors.toList());
        model.addAttribute("waitingBuyList", buysToAccept);
        model.addAttribute("waitingCarList", waitingCarsList);

        return "waitingCarsBuy";

    }

    @PostMapping("/waitingCarsBuy/accept")
    public String acceptStatusToBuy(@RequestParam Long id) {


        purchaseService.changeCarStatus(id, 1L);
        return "redirect:/cars";
    }

    @RequestMapping("/waitingCarsSell")
    public String waitingCarsForSell(Model model) {
        List<Sell> sellsToAccept = sellingService.carWaitingForAccept();
        List<Car> waitingCarsList = sellsToAccept.stream().map(Sell::getCar).collect(Collectors.toList());
        model.addAttribute("waitingSellList", sellsToAccept);
        model.addAttribute("waitingCarList", waitingCarsList);

        return "waitingCarsSell";

    }

    @PostMapping("/waitingCarsSell/accept")
    public String acceptStatusForSell(@RequestParam Long id) {
        sellingService.changeCarStatus(id, 1L);
        return "redirect:/cars";
    }


}