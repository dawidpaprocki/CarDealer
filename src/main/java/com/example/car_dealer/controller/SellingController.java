package com.example.car_dealer.controller;

import com.example.car_dealer.dtos.SellDto;
import com.example.car_dealer.model.Car;
import com.example.car_dealer.model.Customer;
import com.example.car_dealer.model.Sell;
import com.example.car_dealer.model.Worker;
import com.example.car_dealer.service.CarService;
import com.example.car_dealer.service.CustomerService;
import com.example.car_dealer.service.SellingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/sellCar")
public class SellingController {

    private final CarService carService;
    private final SellingService sellingService;
    private final CustomerService customerService;
    @Autowired
    private HttpSession httpSession;

    public SellingController(CarService carService, SellingService sellingService,CustomerService customerService1) {
        this.carService = carService;
        this.sellingService = sellingService;
        this.customerService = customerService1;
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

        Long gettingCarId = car.getId();
        String gettingCarModel = car.getModel();

        model.addAttribute("sell", new SellDto());
        model.addAttribute("carId", gettingCarId);
        httpSession.setAttribute("carId", gettingCarId);
        model.addAttribute("carModel", gettingCarModel);
        model.addAttribute("customer", new Customer());
        return "sellCar";
    }




    @PostMapping("/sell")
    public String sellingCarForAcceptance(
            @ModelAttribute("carModel") String carModel,
            @ModelAttribute("customer") Customer customer,
            @ModelAttribute("sell") SellDto sellDto,
            Model model
    ) throws ParseException {

        Customer addNewCustomer = new Customer();
        addNewCustomer.setPesel(customer.getPesel());
        addNewCustomer.setAddress(customer.getAddress());
        addNewCustomer.setName(customer.getName());
        addNewCustomer.setSurName(customer.getSurName());
        addNewCustomer.setNip(customer.getNip());

        customerService.addCustomer(addNewCustomer);
        Customer customer1 = customerService.getCustomerById(addNewCustomer.getId()).get();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse(sellDto.getDate());

        long carId = (long) httpSession.getAttribute("carId");

        sellingService.sellCar(carId, customer1, sellDto.getAmount(), new Worker(), parse);
        model.addAttribute("feedback", "Wysłano prośbę o akceptacje");
        return "index";
    }


}
