package com.example.car_dealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/accessDenied")
public class AccessDeniedController {

    @Autowired
    private HttpSession httpSession;

    @GetMapping
    public String initBuyCar( Model model
    ) {

        String username = (String) httpSession.getAttribute("userName");
        model.addAttribute("userName",username);

        return "AccessDenied";
    }
}
