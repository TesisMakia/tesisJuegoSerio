package com.juego.serio.services;

import com.juego.serio.entity.Sensor;
import com.juego.serio.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Log4j2
public class SensorService {

    private final Double RANGE_MY01 = 50.0;
    private final Double RANGE_MY02 = 50.0;


    private final SensorRepository sensorRepository;

    @Async("asyncExecutor")
    public Long validateRangeMyo1() throws ExecutionException, InterruptedException {
        long value = 0L;
        var result =  CompletableFuture.completedFuture(sensorRepository.findAll()
                .stream()
                .anyMatch(s -> s.getMyo1() >= RANGE_MY01)).get();

        if (result){
            value = 1L;
            sensorRepository.deleteAll();
        }
      return value;
    }

    @Async("asyncExecutor")
    public  Long validateRangeMyo2()throws ExecutionException, InterruptedException {
        long value= 0L;
        var result= CompletableFuture.completedFuture(sensorRepository.findAll()
                .stream()
                .anyMatch(s -> s.getMyo2() >= RANGE_MY02)).get();
        if (result){
            value=2L;
            sensorRepository.deleteAll();
        }
        return value;
    }
    public List<Sensor> getAll(){
        List<Sensor> all= sensorRepository.findAll();
        return all;
    }
    public Optional<Sensor> getByIdSensor(Long idSensor){
        return sensorRepository.findById(idSensor);
    }
    public Sensor saveSensor(Sensor sensor){
        return sensorRepository.save(sensor);
    }
    public void deleteSensor(Long idSensor){
        sensorRepository.deleteById(idSensor);
    }
}

