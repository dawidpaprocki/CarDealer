package com.example.car_dealer.repository;

import com.example.car_dealer.model.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyRepository extends JpaRepository<Buy,Long> {
}
