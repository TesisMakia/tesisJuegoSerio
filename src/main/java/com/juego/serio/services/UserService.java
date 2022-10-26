package com.juego.serio.services;


import com.juego.serio.entity.User;
import com.juego.serio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor//Lombook crea un constructor con los atributos requeridos
@Slf4j//logear la información
public class UserService {
    private final UserRepository userRepository;
    public List<User> getAll(){
        List<User> all = userRepository.findAll();
        return all;
    }
    public Optional<User> getByIdUser(Long idUser){
        return userRepository.findById(idUser);
    }

    public Optional<User> getByNickname(String nickname){
        return userRepository.findByNickname(nickname);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long idUser){
        userRepository.deleteById(idUser);
    }
    public boolean existsByNickname (String nickName){
        return userRepository.existsByNickname(nickName);
    }
}
