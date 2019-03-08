package com.example.car_dealer.controller;

import com.example.car_dealer.model.User;
import com.example.car_dealer.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String initAddWorker(
            Model model
    ) {
        List<User> usersInDB = userService.findUsersInDB();
        model.addAttribute("listOfUsers", usersInDB);
        return "users";
    }

}
