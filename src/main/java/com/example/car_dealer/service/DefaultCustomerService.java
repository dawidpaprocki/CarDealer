package com.example.car_dealer.service;

import com.example.car_dealer.model.Customer;
import com.example.car_dealer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultCustomerService implements CustomerService {
    CustomerRepository customerRepository;

    public DefaultCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Boolean customerValidate(Customer customer) {
        String customerPesel = customer.getPesel();
            Optional<Customer> c = customerRepository.findByPesel(customerPesel);
        if (!c.isPresent()) {
            customerRepository.save(customer);
            return true;
        } else {
            return false;
        }

    }
}

//        String string = carArgumentCaptorSell.getValue().getDate().toString();
//        Date parsedDate1 = simpleDateFormat.parse(string);