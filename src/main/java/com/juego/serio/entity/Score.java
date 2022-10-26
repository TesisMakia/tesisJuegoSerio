package com.juego.serio.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Data
@Table(name = "score")
@Getter
@Setter
public class Score {
    @Id
    @Column(name = "id_score")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idScore;
    @Column(name = "score")
    private Long score;
   /* @Column(name = "time")
    private Date time;
    @Column(name = "monsters")
    private int monsters;
*/
    @ManyToOne
    @JoinColumn(name="iduser")
    private User user;

    public Score(Long score) {
        this.score = score;
    }
}
