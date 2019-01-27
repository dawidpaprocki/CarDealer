package com.example.car_dealer.service;

import com.example.car_dealer.model.Customer;
import com.example.car_dealer.repository.CustomerRepository;
import org.springframework.stereotype.Service;


public interface CustomerService  {
    Boolean customerValidate(Customer customer);

}
