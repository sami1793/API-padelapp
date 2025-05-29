package com.padelflow.padelapp.padelapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padelflow.padelapp.padelapp.entities.User;
import com.padelflow.padelapp.padelapp.models.request.InfoUserNuevo;
import com.padelflow.padelapp.padelapp.models.response.GenericResponse;
import com.padelflow.padelapp.padelapp.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public GenericResponse createUser(InfoUserNuevo info){
        User user = new User();
        user.setUsername(info.username);
        user.setEmail(info.email);
        user.setPassword(info.password);
        user.setName(info.name);
        user.setLastName(info.lastName);
        user.setIsAdmin(false);

        //Guardar
        userRepository.save(user);

        GenericResponse response =  new GenericResponse();
        response.isOk = true;
        response.message = "User created successfully";
        response.id = user.getUserId();

        return response;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
