package com.example.car_dealer.repository;


import com.example.car_dealer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

   Optional< Customer> findByPesel(String peselNumber);


}
