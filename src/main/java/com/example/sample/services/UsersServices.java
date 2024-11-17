package com.example.sample.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.sample.entity.Users;
import com.example.sample.repository.UsersRepository;

@Service
public class UsersServices {

    @Autowired
    private UsersRepository userRepo;

    public List<Users> getAll() {
        return userRepo.findAll();
    }

    public ResponseEntity<?> usersadd(Users user) {

        try {
            userRepo.save(user);
            // sc.setMessage("AddedSuccessfully");
            return ResponseEntity.status(HttpStatus.OK).body("successfully");

        } catch (Exception e) {
            // sc.setMessage("InternalServeError");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("successfully");

        }

    }
}
