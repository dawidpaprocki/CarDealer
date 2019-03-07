package com.example.car_dealer.controller;

import com.example.car_dealer.dtos.WorkerDto;
import com.example.car_dealer.model.User;
import com.example.car_dealer.model.Worker;
import com.example.car_dealer.service.UserService;
import com.example.car_dealer.service.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/addWorker")
public class WorkerController {
    private final WorkerService workerService;
    private final UserService userService;

    public WorkerController(WorkerService workerService, UserService userService) {
        this.workerService = workerService;
        this.userService = userService;
    }

    @GetMapping
    public String initAddWorker(
            Model model
    ) {
        model.addAttribute("worker", new WorkerDto());
        model.addAttribute("User", new User());
        return "addWorker";
    }

    @PostMapping("/add")
    public String addWorker(@Valid @ModelAttribute("worker") WorkerDto workerDto,     BindingResult bindingResultWorker,
                            @Valid @ModelAttribute("user") User user,     BindingResult bindingResultUser,

                            Model model) throws ParseException {

        if (bindingResultWorker.hasErrors() || bindingResultUser.hasErrors()) {
            return "addWorker";
        }
        Worker addedWorker = new Worker();
        addedWorker.setName(workerDto.getName());
        addedWorker.setSurName(workerDto.getSurName());
        addedWorker.setAddress(workerDto.getAddress());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        addedWorker.setHiredDate(simpleDateFormat.parse(workerDto.getHiredDate()));

        workerService.addWorkerToDB(addedWorker);

        User addedUser = new User();
        addedUser.setUserName(user.getUserName());
        addedUser.setPassword(user.getPassword());
        addedUser.setRoleName(user.getRoleName());
        addedUser.setActive(true);
        addedUser.setWorker(addedWorker);
        userService.addUser(addedUser);

        model.addAttribute("feedback", "Pracownik został dodany");
        return "index";
    }
}
