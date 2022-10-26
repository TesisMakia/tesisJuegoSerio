package com.juego.serio.services;

import com.juego.serio.entity.Score;
import com.juego.serio.entity.User;
import com.juego.serio.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoreService {
    private final ScoreRepository scoreRepository;

    /*public Long getScore(Long fruit, Date time, int monsters){
        return

    }*/
    public Optional<Score> getByIdScore(Long idScore){
        return scoreRepository.findById(idScore);
    }
    public Score saveScore(Score score){
        return scoreRepository.save(score);
    }

}
