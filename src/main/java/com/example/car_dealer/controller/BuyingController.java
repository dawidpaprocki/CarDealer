package com.example.car_dealer.controller;

import com.example.car_dealer.dtos.BuyDto;
import com.example.car_dealer.dtos.CarDto;
import com.example.car_dealer.model.*;
import com.example.car_dealer.service.CarService;
import com.example.car_dealer.service.CustomerService;
import com.example.car_dealer.service.PurchaseService;
import com.example.car_dealer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/buyCar")
public class BuyingController {

    private final PurchaseService purchaseService;
    private final CustomerService customerService;
    private final CarService carService;
    private final UserService userService;
    @Autowired
    private HttpSession httpSession;

    public BuyingController(PurchaseService purchaseService, CustomerService customerService, CarService carService, UserService userService) {
        this.purchaseService = purchaseService;
        this.customerService = customerService;
        this.carService = carService;

        this.userService = userService;
    }

    @GetMapping
    public String initBuyCar(
            Model model
    ) {
        model.addAttribute(new Buy());
        return "buyCar";
    }

    @PostMapping("/bought")
    public String ToCheck(
            @ModelAttribute("buy") BuyDto buyDto,
            Model model
    ) throws ParseException {
        long carId = (long) httpSession.getAttribute("carId");
        long customerId = (long) httpSession.getAttribute("customerId");

        Optional<Car> foundCar = carService.getCarById(carId);
        Optional<Customer> foundCustomer = customerService.getCustomerById(customerId);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date paraseStringToData = simpleDateFormat.parse(buyDto.getDate());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        Optional<User> userInDB = userService.findUserInDB(name);
        Worker activeWorkerGetFromDB = userInDB.get().getWorker();

        purchaseService.buyCar(foundCar.get(), foundCustomer.get(), buyDto.getAmount(), activeWorkerGetFromDB, paraseStringToData);
        model.addAttribute("feedback", "Wysłano prośbę o akceptacje");
        return "index";

    }

    @RequestMapping("/addCar")
    public String addCar(Model model) {
        model.addAttribute("car", new CarDto());
        model.addAttribute("customer", new Customer());
        return "addCar";
    }

    @RequestMapping("/addCustomer")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "addCustomer";
    }

    @RequestMapping("/selectCustomer")
    public String selectCustomer(Model model) {
        List<Customer> listOfCustomers = customerService.getListOfCustomers();
        model.addAttribute("listOfCustomers", listOfCustomers);
        model.addAttribute("customer", new Customer());
        return "ourCustomers";
    }


    @PostMapping("/addCustomer")
    public String addCustomer(
            @Valid  @ModelAttribute("customer") Customer customer,BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "/addCustomer";
        }

        Customer addNewCustomer = new Customer();
        addNewCustomer.setPesel(customer.getPesel());
        addNewCustomer.setAddress(customer.getAddress());
        addNewCustomer.setName(customer.getName());
        addNewCustomer.setSurName(customer.getSurName());
        addNewCustomer.setNip(customer.getNip());
        customerService.addCustomer(addNewCustomer);

        Long newCustomerId = addNewCustomer.getId();
        String newCustomerName = addNewCustomer.getName();

        model.addAttribute("customerId", newCustomerId);
        model.addAttribute("customerName", newCustomerName);
        model.addAttribute("buy", new Buy());

        httpSession.setAttribute("customerId", newCustomerId);
        httpSession.setAttribute("customerName", newCustomerName);

        return "buyCar";
    }

    @PostMapping("/selectCustomer")
    public String selectCustomer(
            @RequestParam String selectedCustomerName,
            @RequestParam Long selectedCustomerId,
            Model model
    ) {
        model.addAttribute("customerId", selectedCustomerId);
        model.addAttribute("customerName", selectedCustomerName);
        model.addAttribute("buy", new Buy());

        httpSession.setAttribute("customerId", selectedCustomerId);
        httpSession.setAttribute("customerName", selectedCustomerName);

        return "buyCar";
    }


    @PostMapping("/addCar")
    public String addCar(
            @Valid @ModelAttribute("car") CarDto carDto,
            BindingResult bindingResult,
            Model model
    ) throws ParseException {
        if (bindingResult.hasErrors()) {
            return "/addCar";
        }
        Car boughtCar = new Car();
        boughtCar.setDescription(carDto.getDescription());
        boughtCar.setMileage(carDto.getMileage());
        boughtCar.setEngine(carDto.getEngine());
        boughtCar.setFuelType(carDto.getFuelType());
        boughtCar.setModel(carDto.getModel());
        boughtCar.setNrOc(carDto.getNrOc());
        boughtCar.setNrRegister(carDto.getNrRegister());
        boughtCar.setPower(carDto.getPower());
        boughtCar.setTestDriveAmount(0L);
        boughtCar.setGearBox(carDto.getGearBox());
        boughtCar.setSold(0L);
        boughtCar.setOwner(carDto.getOwner());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        boughtCar.setDate(simpleDateFormat.parse(carDto.getDate()));

        carService.addCarToDB(boughtCar);

        model.addAttribute("customerId", httpSession.getAttribute("customerId"));
        model.addAttribute("customerName", httpSession.getAttribute("customerName"));

        Long boughtCarId = boughtCar.getId();
        String boughtCarModel = boughtCar.getModel();

        model.addAttribute("carId", boughtCarId);
        model.addAttribute("carModel", boughtCarModel);
        model.addAttribute("buy", new Buy());

        httpSession.setAttribute("carId", boughtCarId);
        httpSession.setAttribute("carModel", boughtCarModel);

        return "buyCar";
    }
}
