package com.example.car_dealer;

import com.example.car_dealer.model.*;
import com.example.car_dealer.repository.BuyRepository;
import com.example.car_dealer.repository.CarRepository;
import com.example.car_dealer.repository.CustomerRepository;
import com.example.car_dealer.repository.WorkerRepository;
import com.example.car_dealer.service.DefaultPurchaseService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DefaultPurchaseServiceTest {

    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    private BuyRepository buyRepository;
    private WorkerRepository workerRepository;
    private DefaultPurchaseService defaultPurchaseService;
    private Customer customer;
    private Worker worker;
    private Car car;
    private Buy buy;


    @Before
    public void before() {
        carRepository = mock(CarRepository.class);
        customerRepository = mock(CustomerRepository.class);
        buyRepository = mock(BuyRepository.class);
        workerRepository = mock(WorkerRepository.class);

        defaultPurchaseService = new DefaultPurchaseService(
                customerRepository, workerRepository, carRepository, buyRepository
        );

        car = new Car();
        car.setId(1L);

        customer = new Customer();
        customer.setId(1L);

        worker = new Worker();
        worker.setId(1L);

        buy = new Buy();
        car.setId(1L);
    }



    @Test
    public void PurchaseCarTestTrue() throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date parsedDate = simpleDateFormat.parse("1990-01-01");

        defaultPurchaseService.buyCar(car, customer,
                BigDecimal.TEN, worker, parsedDate);

        ArgumentCaptor<Buy> carArgumentCaptorBuy = ArgumentCaptor.forClass(Buy.class);

        verify(buyRepository).save(carArgumentCaptorBuy.capture());

        assertEquals(customer.getId(), carArgumentCaptorBuy.getValue().getCustomer().getId());
        assertEquals(worker.getId(), carArgumentCaptorBuy.getValue().getWorker().getId());
        assertEquals(car.getId(), carArgumentCaptorBuy.getValue().getCar().getId());



    }

    @Test
    public void sellCarTestFalse() throws ParseException {


        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date parsedDate = simpleDateFormat.parse("1990-01-01");


        defaultPurchaseService.buyCar(car, customer,
                BigDecimal.TEN, worker, parsedDate);

        ArgumentCaptor<Buy> carArgumentCaptorBuy = ArgumentCaptor.forClass(Buy.class);

        verify(buyRepository).save(carArgumentCaptorBuy.capture());

        assertNotEquals(new Long(0), carArgumentCaptorBuy.getValue().getCustomer().getId());
        assertNotEquals(new Long(0), carArgumentCaptorBuy.getValue().getWorker().getId());
        assertNotEquals(new Long(0), carArgumentCaptorBuy.getValue().getCar().getId());

//        String string = carArgumentCaptorSell.getValue().getDate().toString();
//        Date parsedDate1 = simpleDateFormat.parse(string);
    }

    @Test
    public void carStatusTestTrue()  {
        buy.setCar(car);

        when(buyRepository.findById(any())).thenReturn(Optional.of(buy));
        when(carRepository.findById(any())).thenReturn(Optional.of(car));

        defaultPurchaseService.changeCarStatus(1L,1L);

        ArgumentCaptor<Buy> buyArgumentCaptorBuy = ArgumentCaptor.forClass(Buy.class);
        verify(buyRepository).save(buyArgumentCaptorBuy.capture());

        assertEquals(new Long(1), buyArgumentCaptorBuy.getValue().getAcceptStatus());

    }

    @Test
    public void carStatusTestFalse()  {
        buy.setCar(car);

        when(buyRepository.findById(any())).thenReturn(Optional.of(buy));
        when(carRepository.findById(any())).thenReturn(Optional.of(car));

        defaultPurchaseService.changeCarStatus(1L,0L);

        ArgumentCaptor<Buy> buyArgumentCaptorBuy = ArgumentCaptor.forClass(Buy.class);
        verify(buyRepository).save(buyArgumentCaptorBuy.capture());

        assertNotEquals(new Long(1), buyArgumentCaptorBuy.getValue().getAcceptStatus());

    }
}
