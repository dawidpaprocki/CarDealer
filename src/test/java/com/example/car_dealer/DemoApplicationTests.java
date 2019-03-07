package com.example.car_dealer;

import com.example.car_dealer.model.Car;
import com.example.car_dealer.repository.CarRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    CarRepository carRepository;

    @Test
    public void CarRepositoryFindAll() {
        List<Car> bySoldFalse = carRepository.findAll();
        Assert.assertTrue(bySoldFalse.size() > 0);

        List<Car> findAllIsNotSold = carRepository.findAllIsNotSold();
        Assert.assertTrue(findAllIsNotSold.size() > 0);

        List<Car> findAllIsSold = carRepository.findAllIsSold();
        Assert.assertTrue(findAllIsSold.size() <= 0);


        Optional<Car> carById = carRepository.findById(1L);
        Assert.assertTrue(carById.get().getId() == 1L);
    }

//    @Test
//    public void CarRepositoryFindCarBetweenDate()  throws ParseException{
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date afterDate = dateFormat.parse("1991-01-01");
//        Date beforeDate = dateFormat.parse("2011-01-01");
//        List<Car> allByYearBetween = carRepository.findAllBetweenDates(afterDate, beforeDate);
//        Optional<Long> id = Optional.ofNullable(allByYearBetween.get(0).getId());
//        Assert.assertTrue(id.get() == 1L);
//    }

    @Test
    public void CarRepositoryCarFindCarAvailable(){

        Optional<Car> availableCarById = carRepository.findAvailableCarById(1L);
        Assert.assertTrue(availableCarById.get().getId() == 1L);

    }




}

