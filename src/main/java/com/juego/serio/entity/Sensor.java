package com.juego.serio.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table (name="sensor")
public class Sensor {
    @Id
    @Column(name = "id_sensor")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idSensor;
    @Column(name = "myo1")
    private double myo1;
    @Column(name = "myo2")
    private double myo2;

    @OneToMany(mappedBy = "sensor")
    private List<UserSensor> userSensors;

    public Sensor() {
    }

    public Sensor(double myo1, double myo2) {
        this.myo1 = myo1;
        this.myo2 = myo2;
    }
}
