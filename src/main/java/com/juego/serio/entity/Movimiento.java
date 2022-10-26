package com.juego.serio.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
@Getter
@Setter
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMov;
    @ManyToOne
    @JoinColumn(name="iduser")
    private User user;

    @ManyToOne
    @JoinColumn(name="idsensor")
    private Sensor sensor;
}
