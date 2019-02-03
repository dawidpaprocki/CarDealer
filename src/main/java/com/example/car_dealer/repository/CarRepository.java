package com.example.car_dealer.repository;
import com.example.car_dealer.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    @Query("FROM Car Where Sold = 0 ")
    List <Car> findAllIsNotSold();

    @Query("FROM Car Where Sold = 1")
    List <Car> findAllIsSold();


    @Query("FROM Car c Where c.date >= :afterDate and c.date <= :beforeDate")
    List <Car> findAllBetweenDates(@Param ("afterDate") Date afterDate,@Param ("beforeDate")  Date beforeDate);

    @Query("FROM Car c WHERE c.id = :carId AND c.sold = 0")
    Optional<Car> findAvailableCarById(@Param("carId") Long carId);
}
