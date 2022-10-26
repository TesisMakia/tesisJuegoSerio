package com.juego.serio.controller;

import com.juego.serio.entity.Sensor;
import com.juego.serio.services.SensorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/sensor/")
@RequiredArgsConstructor
@Log4j2
public class SensorController {
    private final SensorService sensorService;
    @GetMapping("get")
    public List<Sensor> getAll(){
        return sensorService.getAll();
    }

    @GetMapping("get/{idSensor}")
    public ResponseEntity<Sensor> getByIdSensor(@PathVariable("idSensor") Long idSensor){
        Optional<Sensor> sensor = sensorService.getByIdSensor(idSensor);
        if(sensor.isPresent()){
            return new ResponseEntity<>(sensor.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<Sensor> saveSensor(@RequestBody Sensor sensor){
        try{
            Sensor sensor1= sensorService.saveSensor(new Sensor(sensor.getMyo1(),sensor.getMyo2()));
            return new ResponseEntity<>(sensor1, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{idSensor}")
    public ResponseEntity<HttpStatus> deleteSensor (@PathVariable("idSensor") Long idSensor){
        try{
            sensorService.deleteSensor(idSensor);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/get-range01")
    @Scheduled(fixedRate = 30000)
    ResponseEntity<Long> getMy01() throws ExecutionException, InterruptedException{
        log.info("Fixed Rate Task :: Execution Time - {}", LocalDateTime.now());
        var sensor = sensorService.validateRangeMyo1();
        return new ResponseEntity<>(sensor,HttpStatus.OK);
    }
    @GetMapping("/get-range02")
    @Scheduled(fixedRate = 30000)
    ResponseEntity<Long> getMy02() throws ExecutionException, InterruptedException{
        log.info("Fixed Rate Task :: Execution Time - {}", LocalDateTime.now());
        var sensor = sensorService.validateRangeMyo2();
        return new ResponseEntity<>(sensor,HttpStatus.OK);
    }
}
