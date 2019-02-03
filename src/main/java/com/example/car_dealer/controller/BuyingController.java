package com.example.car_dealer.controller;

import com.example.car_dealer.dtos.BuyDto;
import com.example.car_dealer.dtos.CarDto;
import com.example.car_dealer.model.Buy;
import com.example.car_dealer.model.Car;
import com.example.car_dealer.model.Customer;
import com.example.car_dealer.model.Worker;
import com.example.car_dealer.service.DefaultCarService;
import com.example.car_dealer.service.DefaultCustomerService;
import com.example.car_dealer.service.DefaultPurchaseService;
import com.example.car_dealer.service.DefaultWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/buyCar")
public class BuyingController {

    private final DefaultPurchaseService defaultPurchaseService;
    private final DefaultCustomerService defaultCustomerService;
    private final DefaultCarService defaultCarService;
    private final DefaultWorkerService defaultWorkerService;
    @Autowired
    private HttpSession httpSession;

    public BuyingController(DefaultPurchaseService defaultPurchaseService, DefaultCustomerService defaultCustomerService, DefaultCarService defaultCarService, DefaultWorkerService defaultWorkerService) {
        this.defaultPurchaseService = defaultPurchaseService;
        this.defaultCustomerService = defaultCustomerService;
        this.defaultCarService = defaultCarService;
        this.defaultWorkerService = defaultWorkerService;
    }

    @GetMapping
    public String initBuyCar(
            Model model
    ){
        model.addAttribute(new Buy());
        return "buyCar";
    }

    @PostMapping("/bought")
    public String ToCheck(
            @ModelAttribute("buy")BuyDto buyDto
    ) throws ParseException {
        long carId =(long) httpSession.getAttribute("carId");
        long customerId =(long) httpSession.getAttribute("customerId");

        Optional<Car> foundCar = defaultCarService.getCarById(carId);
        Optional<Customer> foundCustomer = defaultCustomerService.getCustomerById(customerId);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse(buyDto.getDate());

        Worker worker = new Worker();
        worker.setId(1L);
        worker.setName("Name");
        defaultWorkerService.addWorkerToDB(worker);
        defaultPurchaseService.buyCar(foundCar.get(),foundCustomer.get(),buyDto.getAmount(),worker,parse);
        return "bought";

    }

    @RequestMapping("/addCar")
    public String addCar( Model model ){
        model.addAttribute("car",new CarDto());
        model.addAttribute("customer",new Customer());
        return "addCar";
    }
    @RequestMapping("/addCustomer")
    public String addCustomer( Model model ){
        model.addAttribute("customer",new Customer());
        return "addCustomer";
    }


    @PostMapping("/addCustomer")
    public String addCustomer(
            @ModelAttribute("customer") Customer customer,
            Model model
    ){
        Customer addNewCustomer = new Customer();
        addNewCustomer.setPesel(customer.getPesel());
        addNewCustomer.setAdress(customer.getAdress());
        addNewCustomer.setName(customer.getName());
        addNewCustomer.setSurName(customer.getSurName());
        addNewCustomer.setNip(customer.getNip());
        defaultCustomerService.addCustomer(addNewCustomer);

        Long id = addNewCustomer.getId();
        String name = addNewCustomer.getName();

        model.addAttribute("customerId" ,id);
        model.addAttribute("name" ,name);
        model.addAttribute("buy" ,new Buy());

        httpSession.setAttribute("customerId",id);
        httpSession.setAttribute("name",name);

        return "buyCar";
    }



    @PostMapping("/addCar")
    public String addCar(
            @ModelAttribute("car") CarDto car,
            Model model
    ) throws ParseException {
        Car boughtCar = new Car();
        boughtCar.setDescription(car.getDescription());
        boughtCar.setMileage(car.getMileage());
        boughtCar.setEngine(car.getEngine());
        boughtCar.setFuelType(car.getFuelType());
        boughtCar.setModel(car.getModel());
        boughtCar.setNrOc(car.getNrOc());
        boughtCar.setNrRegister(car.getNrRegister());
        boughtCar.setPower(car.getPower());
        boughtCar.setTestDriveAmount(0L);
        boughtCar.setGearBox(car.getGearBox());
        boughtCar.setSold(0L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        boughtCar.setDate(simpleDateFormat.parse(car.getDate()));
        defaultCarService.addCarToDB(boughtCar);

        model.addAttribute("customerId",httpSession.getAttribute("customerId"));
        model.addAttribute("name",httpSession.getAttribute("name"));

        Long id = boughtCar.getId();
        String carModel = boughtCar.getModel();

        model.addAttribute("carId" ,id);
        model.addAttribute("model" ,carModel);
        model.addAttribute("buy" ,new Buy());

        httpSession.setAttribute("carId",id);
        httpSession.setAttribute("model",carModel);

        return "buyCar";
    }
}