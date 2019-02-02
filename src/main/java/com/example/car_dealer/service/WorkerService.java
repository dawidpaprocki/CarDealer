package com.example.car_dealer.service;

import com.example.car_dealer.model.Worker;

public interface WorkerService {

    Boolean addWorkerToDB(Worker worker);
    Boolean deleteWorkerFromDB(Worker worker);
    Boolean updateWorkerFromDB(Worker worker);

}
