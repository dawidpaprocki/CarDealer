package com.example.car_dealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class homeController {
    @RequestMapping
    public String initHomePage(){
        return "index";
    }

}
