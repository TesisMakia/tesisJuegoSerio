package com.juego.serio.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data//crea el to String, hash code y los get y set
@Table(name = "users")
@AllArgsConstructor // crea un solo constructor con todos los argumentos
@NoArgsConstructor //el constructor por defecto sin argumentos
@RequiredArgsConstructor //crea un constructor que inicializa todos los atributos final, requeridos
public class User {
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;

    @NonNull //Cuando se cree un objeto no puede ser nulo-> atributo final->
    @Column(name = "nickname")
    private String nickname;

    @NonNull //Cuando se cree un objeto no puede ser nulo
    @Column(name = "email")
    private String email;

    @NonNull
    @Column
    private String password;

    @OneToMany(mappedBy = "user")
    private List<UserSensor> userSensors;
    @OneToMany(mappedBy = "score")
    private List<Score> score;

}
