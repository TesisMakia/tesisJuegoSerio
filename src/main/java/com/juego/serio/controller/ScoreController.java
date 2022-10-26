package com.juego.serio.controller;


import com.juego.serio.entity.Score;
import com.juego.serio.services.ScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/api/v1/score/")
@RequiredArgsConstructor
@Slf4j
public class ScoreController {
    private final ScoreService scoreService;
    @GetMapping("{idScore}")
    public ResponseEntity<Score> getByIdScore(@PathVariable("idScore") Long idScore){
        Optional<Score>score = scoreService.getByIdScore(idScore);
        if (score.isPresent()){
            return new ResponseEntity<>(score.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("update/{idScore}")
    public ResponseEntity<Score> updateScore(@PathVariable ("idScore") Long idScore, @RequestBody Score score){
        Optional<Score> scoreData = scoreService.getByIdScore(idScore);
        if(scoreData.isPresent()){
            Score scoreUpdate= scoreService.getByIdScore(idScore).get();
            scoreUpdate.setScore(score.getScore());
            return new ResponseEntity<>(scoreService.saveScore(scoreUpdate), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/savescore")
    public ResponseEntity<Score> saveScore(@RequestBody Score score){
        try{
            Score scoreCreate = scoreService.saveScore(new Score(score.getScore()));
            return new ResponseEntity<>(scoreCreate,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
