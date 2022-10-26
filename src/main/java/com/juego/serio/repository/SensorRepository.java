package com.juego.serio.repository;

import com.juego.serio.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SensorRepository extends JpaRepository<Sensor,Long > {

}
