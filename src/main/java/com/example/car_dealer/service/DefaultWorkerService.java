package com.example.car_dealer.service;

import com.example.car_dealer.model.Worker;
import com.example.car_dealer.repository.WorkerRepository;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultWorkerService implements WorkerService{

    private final WorkerRepository workerRepository;

    public DefaultWorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    private Boolean checkIfPresent(Worker worker){

        Optional<Worker> foundWorker = workerRepository.findByNameAndSurNameAndHiredDate(
                worker.getName(),
                worker.getSurName(),
                worker.getHiredDate()
        );

        return foundWorker.isPresent();
    }


    @Override
    public Boolean addWorkerToDB(Worker worker) {
        if(!checkIfPresent(worker)){
            workerRepository.save(worker);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean deleteWorkerFromDB(Worker worker) {
        if(!checkIfPresent(worker)){
            workerRepository.delete(worker);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean updateWorkerFromDB(Worker worker) {
        Optional<Worker> foundWorker = workerRepository.findById(worker.getId());
        if(foundWorker.isPresent()){
            workerRepository.save(worker);
            return true;
        }else {
            return false;
        }
    }
}
