package com.example.car_dealer.controller;

import com.example.car_dealer.model.Car;
import com.example.car_dealer.model.Customer;
import com.example.car_dealer.model.Sell;
import com.example.car_dealer.model.Worker;
import com.example.car_dealer.service.CarService;
import com.example.car_dealer.service.DefaultSellingService;
import com.example.car_dealer.service.SellingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/sellCar")
public class SellingController {

    private final CarService carService;
    private final SellingService sellingService;
    private final DefaultSellingService defaultSellingService;

    public SellingController(CarService carService, SellingService sellingService, DefaultSellingService defaultSellingService) {
        this.carService = carService;
        this.sellingService = sellingService;
        this.defaultSellingService = defaultSellingService;
    }

    @GetMapping("/{id}")
    public String initCarSellForm(@PathVariable("id") Long carId,
                                  Model model) {
        Optional<Car> foundCar = carService.getCarById(carId);
        Car car;
        if (foundCar.isPresent()) {
            car = foundCar.get();
        } else {
            return "redirect:cars/";
        }
        model.addAttribute("sell", new Sell());
        model.addAttribute("car", car);
        model.addAttribute("customer", new Customer());
        model.addAttribute("date", new Date());
        return "sellCar";
    }

    @PostMapping("/sell")
    public String sellingCarForAcceptance(
            @ModelAttribute("car") Car car,
            @ModelAttribute("customer") Customer customer,
            @ModelAttribute("sell") Sell sell,
            @ModelAttribute("date") String date
    ) throws ParseException {
        Date parsedDate;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            parsedDate = simpleDateFormat.parse(date);

        sell.setCustomer(customer);
        sell.setAcceptStatus(0L);
        defaultSellingService.sellCar(car.getId(), customer, sell.getAmount(), new Worker(), parsedDate);
        return "redirect:/cars";
    }


}
