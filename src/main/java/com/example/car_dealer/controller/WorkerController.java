package com.example.car_dealer.controller;

import com.example.car_dealer.dtos.WorkerDto;
import com.example.car_dealer.model.Buy;
import com.example.car_dealer.model.Worker;
import com.example.car_dealer.service.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/addWorker")
public class WorkerController {
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping
    public String initAddWorker(
            Model model
    ) {
        model.addAttribute("worker", new Worker());
        return "addWorker";
    }

    @PostMapping("/add")
    public String addWorker(@ModelAttribute("worker") WorkerDto workerDto, Model model) throws ParseException {
        Worker addedWorker = new Worker();
        addedWorker.setName(workerDto.getName());
        addedWorker.setSurName(workerDto.getSurName());
        addedWorker.setAddress(workerDto.getAddress());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse(workerDto.getHiredDate());

        addedWorker.setHiredDate(parse);
        workerService.addWorkerToDB(addedWorker);
        model.addAttribute("feedback", "Pracownik został dodany");
        return "index";
    }
}
