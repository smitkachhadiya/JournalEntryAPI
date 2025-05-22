package com.JournalAppDemo.JournalApp.controller;

import com.JournalAppDemo.JournalApp.entity.User;
import com.JournalAppDemo.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/allUsers")
    public ResponseEntity<?> getAllUser(){
        List<User> all = userService.getAll();
        if(!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<?> addAdmin(@RequestBody User user){
        try {
            userService.saveAdmin(user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
