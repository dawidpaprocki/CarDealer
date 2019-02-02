package com.example.car_dealer;

import com.example.car_dealer.model.Customer;
import com.example.car_dealer.repository.CustomerRepository;
import com.example.car_dealer.service.DefaultCustomerService;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class DefaultCustomerServiceTest {
    private Customer customer;
    private DefaultCustomerService defaultCustomerService;
    private CustomerRepository customerRepository;




    @Before
    public void before(){
        customerRepository = mock(CustomerRepository.class);
        defaultCustomerService = new DefaultCustomerService(customerRepository);
        customer = new Customer();
     }

    @Test
    public void customerValidateFalse(){
        Customer testCustomer = new Customer();

        when(customerRepository.findByPesel(any())).thenReturn(Optional.of(customer));

        Boolean isCustomerSaved = defaultCustomerService.customerValidate(testCustomer);
        assertFalse(isCustomerSaved);

    }


    @Test
    public void customerValidateTrue(){
        Customer testCustomer = new Customer();

        when(customerRepository.findByPesel(any())).thenReturn(Optional.ofNullable(null));

        Boolean isCustomerSaved = defaultCustomerService.customerValidate(testCustomer);

        assertTrue(isCustomerSaved);

    }


}
