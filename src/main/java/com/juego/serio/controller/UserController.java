package com.juego.serio.controller;

import com.juego.serio.entity.User;
import com.juego.serio.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController//controlador
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor//Genera constructor con ciertos datos especificos
@Slf4j//logear informacion errores-logger
public class UserController {
    private final UserService userService;
   // @CrossOrigin(origins ="http://localhost:5500")
    @GetMapping("get")
    public List<User> getAll(){
        return userService.getAll();
    }
    @GetMapping("/id/{idUser}")
    public ResponseEntity<User> getByIdUser(@PathVariable("idUser") Long idUser){
        Optional<User> user = userService.getByIdUser(idUser);
        if (user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/nick/{nickname}")
    public ResponseEntity<User> getByNickname(@PathVariable("nickname") String nickname){
        if(!userService.existsByNickname(nickname)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            User user = userService.getByNickname(nickname).get();
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
    }

    @PutMapping("update/{idUser}")
    public ResponseEntity<User> updateUser(@PathVariable("idUser") Long idUser, @RequestBody User user){
        Optional<User> userData = userService.getByIdUser(idUser);
        if(userData.isPresent()){
            User userUpdate= userService.getByIdUser(idUser).get();
            userUpdate.setNickname(user.getNickname());
            userUpdate.setEmail(user.getEmail());
            return new ResponseEntity<>(userService.saveUser(userUpdate),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        try{
            User userCreate = userService.saveUser(new User(user.getNickname(),user.getEmail(),user.getPassword()));
            return new ResponseEntity<>(userCreate,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("idUser") Long idUser){
        try{
            userService.deleteUser(idUser);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

