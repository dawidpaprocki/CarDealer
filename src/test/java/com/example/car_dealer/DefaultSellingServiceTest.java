package com.example.car_dealer;

import com.example.car_dealer.model.*;
import com.example.car_dealer.repository.*;
import com.example.car_dealer.service.DefaultPurchaseService;
import com.example.car_dealer.service.DefaultSellingService;
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
import static org.mockito.Mockito.*;


public class DefaultSellingServiceTest {

    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    private SellRepository sellRepository;
    private WorkerRepository workerRepository;
    private DefaultSellingService defaultSellingService;
    private DefaultPurchaseService defaultPurchaseService;
    private Customer customer;
    private Worker worker;
    private Car car;
    private Sell sell;



    @Test
    @Before
    public void before() {
        carRepository = mock(CarRepository.class);
        customerRepository = mock(CustomerRepository.class);
        sellRepository = mock(SellRepository.class);
        workerRepository = mock(WorkerRepository.class);

        defaultSellingService = new DefaultSellingService(
                customerRepository, workerRepository, carRepository, sellRepository
        );

        car = new Car();
        car.setSold(0L);
        car.setId(1L);

        customer = new Customer();
        customer.setId(1L);

        worker = new Worker();
        worker.setId(1L);

        sell = new Sell();
        sell.setId(1L);
    }

    @Test
    public void sellCarTestTrue() throws ParseException {


        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date parsedDate = simpleDateFormat.parse("1990-01-01");


        when(carRepository.findById(any())).thenReturn(Optional.of(car));

        defaultSellingService.sellCar(1L, customer,
                BigDecimal.TEN, worker, parsedDate);

        ArgumentCaptor<Car> carArgumentCaptorCar = ArgumentCaptor.forClass(Car.class);
        ArgumentCaptor<Sell> carArgumentCaptorSell = ArgumentCaptor.forClass(Sell.class);

        verify(carRepository).save(carArgumentCaptorCar.capture());
        verify(sellRepository).save(carArgumentCaptorSell.capture());


        assertEquals(new Long(1), carArgumentCaptorCar.getValue().getSold());
        assertEquals(customer.getId(), carArgumentCaptorSell.getValue().getCustomer().getId());
        assertEquals(worker.getId(), carArgumentCaptorSell.getValue().getWorker().getId());
        assertEquals(car.getId(), carArgumentCaptorSell.getValue().getCar().getId());

//        String string = carArgumentCaptorSell.getValue().getDate().toString();
//        Date parsedDate1 = simpleDateFormat.parse(string);
    }

    @Test
    public void sellCarTestFalse() throws ParseException {


        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date parsedDate = simpleDateFormat.parse("1990-01-01");



        when(carRepository.findById(any())).thenReturn(Optional.of(car));

         defaultSellingService.sellCar(1L, customer,
                BigDecimal.TEN, worker, parsedDate);

        ArgumentCaptor<Car> carArgumentCaptorCar = ArgumentCaptor.forClass(Car.class);
        ArgumentCaptor<Sell> carArgumentCaptorSell = ArgumentCaptor.forClass(Sell.class);

        verify(carRepository).save(carArgumentCaptorCar.capture());
        verify(sellRepository).save(carArgumentCaptorSell.capture());


        assertNotEquals(new Long(0), carArgumentCaptorCar.getValue().getSold());
        assertNotEquals(new Long(10), carArgumentCaptorSell.getValue().getCustomer().getId());
        assertNotEquals(new Long(10), carArgumentCaptorSell.getValue().getWorker().getId());
        assertNotEquals(new Long(10), carArgumentCaptorSell.getValue().getCar().getId());

//        String string = carArgumentCaptorSell.getValue().getDate().toString();
//        Date parsedDate1 = simpleDateFormat.parse(string);
    }

    @Test
    public void carStatusTestTrue(){
        sell.setCar(car);

        when(sellRepository.findById(any())).thenReturn(Optional.of(sell));
        when(carRepository.findById(any())).thenReturn(Optional.of(car));
        defaultSellingService.carStatus(1L,1L);

        ArgumentCaptor<Sell> carArgumentCaptorSell = ArgumentCaptor.forClass(Sell.class);
        verify(sellRepository).save(carArgumentCaptorSell.capture());

        assertEquals(new Long(1L),carArgumentCaptorSell.getValue().getAcceptStatus());

    }
    @Test
    public void carStatusTestFalse() {
        sell.setCar(car);

        when(sellRepository.findById(any())).thenReturn(Optional.of(sell));
        when(carRepository.findById(any())).thenReturn(Optional.of(car));
        defaultSellingService.carStatus(1L,0L);

        ArgumentCaptor<Sell> carArgumentCaptorSell = ArgumentCaptor.forClass(Sell.class);
        verify(sellRepository).save(carArgumentCaptorSell.capture());

        assertNotEquals(new Long(1L),carArgumentCaptorSell.getValue().getAcceptStatus());
    }





    }
