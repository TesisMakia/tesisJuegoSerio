package com.juego.serio.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "motion")
@Getter
@Setter
public class UserSensor {
    @Id
    @Column(name = "id_motion")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMotion;
    @ManyToOne
    @JoinColumn(name="iduser")
    private User user;

    @ManyToOne
    @JoinColumn(name="idsensor")
    private Sensor sensor;


}
