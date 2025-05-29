package com.padelflow.padelapp.padelapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.padelflow.padelapp.padelapp.entities.User;
import com.padelflow.padelapp.padelapp.models.request.InfoUserNuevo;
import com.padelflow.padelapp.padelapp.models.response.GenericResponse;
import com.padelflow.padelapp.padelapp.models.response.InfoUserResponse;
import com.padelflow.padelapp.padelapp.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController (UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<GenericResponse> createUser(@RequestBody InfoUserNuevo info){
        GenericResponse response = userService.createUser(info);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<InfoUserResponse>> getAllUsers(){
        List<InfoUserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
