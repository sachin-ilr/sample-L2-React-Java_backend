package com.example.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.entity.Users;
import com.example.sample.repository.UsersRepository;
import com.example.sample.service.UsersServices;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository userRepo;
    @Autowired
    private UsersServices userServ;

    @GetMapping("/all")
    public List<Users> getAll() {
        return userRepo.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> usersadd(@RequestBody Users user) {
        return userServ.usersadd(user);
    }

    @DeleteMapping("/delete/{id}")
    public void usersdelete(@PathVariable("id") Long id) {
        userRepo.deleteById(id);
    }

}
