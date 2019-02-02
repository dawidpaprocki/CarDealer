package com.example.car_dealer;

import com.example.car_dealer.model.Buy;
import com.example.car_dealer.model.Worker;
import com.example.car_dealer.repository.WorkerRepository;
import com.example.car_dealer.service.DefaultWorkerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DefaultWorkerServiceTest {

    private WorkerRepository workerRepository;
    private Worker worker;
    private Worker workerTest;
    private DefaultWorkerService defaultWorkerService;


    @Before
    public void before() {
        workerRepository = mock(WorkerRepository.class);

        defaultWorkerService = new DefaultWorkerService(
                workerRepository
        );

        worker = new Worker();
        workerTest = new Worker();
    }

    @Test
    public void addWorkerToDBTestFalse() {

        when(workerRepository.findByNameAndSurNameAndHiredDate(any(), any(), any()))
                .thenReturn(Optional.of(worker));

        Boolean isWorkerSaved = defaultWorkerService.addWorkerToDB(workerTest);

        assertFalse(isWorkerSaved);
    }

    @Test
    public void addWorkerToDBTestTrue() {

        when(workerRepository.findByNameAndSurNameAndHiredDate(any(), any(), any()))
                .thenReturn(Optional.ofNullable(null));

        Boolean isWorkerSaved = defaultWorkerService.addWorkerToDB(workerTest);

        assertTrue(isWorkerSaved);
    }

    @Test
    public void deleteWorkerFromDBTestFalse(){
        when(workerRepository.findByNameAndSurNameAndHiredDate(any(),any(),any()))
                .thenReturn(Optional.of(worker));

        Boolean isWorkerDeleted = defaultWorkerService.deleteWorkerFromDB(workerTest);

        assertFalse(isWorkerDeleted);

    }

    @Test
    public void deleteWorkerFromDBTestTrue(){
        when(workerRepository.findByNameAndSurNameAndHiredDate(any(),any(),any()))
                .thenReturn(Optional.ofNullable(null));

        Boolean isWorkerDeleted = defaultWorkerService.deleteWorkerFromDB(workerTest);

        assertTrue(isWorkerDeleted);

    }


    @Test
    public void updateWorkerFromDBTestFalse(){

        worker.setName("Worker");
        worker.setSurName("Worker Sur");

        workerTest.setName("Changed Name");
        workerTest.setSurName("Changed Sur");

        when(workerRepository.findById(any()))
                .thenReturn(Optional.ofNullable(null));

        defaultWorkerService.updateWorkerFromDB(workerTest);

        ArgumentCaptor<Worker> workerArgumentCaptor = ArgumentCaptor.forClass(Worker.class);

        verify(workerRepository).save(workerArgumentCaptor.capture());


        assertNotEquals("Changed Name",workerArgumentCaptor.getValue().getName());
        assertNotEquals("Changed Sur",workerArgumentCaptor.getValue().getSurName());
    }

    @Test
    public void updateWorkerFromDBTestTrue(){

        worker.setName("Worker Name");
        worker.setSurName("Worker Sur");

        workerTest.setName("Changed Name");
        workerTest.setSurName("Changed Sur");

        when(workerRepository.findById(any()))
                .thenReturn(Optional.of(worker));

        defaultWorkerService.updateWorkerFromDB(workerTest);

        ArgumentCaptor<Worker> workerArgumentCaptor = ArgumentCaptor.forClass(Worker.class);

        verify(workerRepository).save(workerArgumentCaptor.capture());

        assertEquals("Changed Name",workerArgumentCaptor.getValue().getName());
        assertEquals("Changed Sur",workerArgumentCaptor.getValue().getSurName());
    }


}
