package com.example.car_dealer.repository;

import com.example.car_dealer.model.Customer;
import com.example.car_dealer.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    Optional< Worker> findByNameAndSurNameAndHiredDate(String name,String surName,Date date);
}
