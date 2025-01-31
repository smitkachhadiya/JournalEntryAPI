package com.JournalAppDemo.JournalApp.controller;

import com.JournalAppDemo.JournalApp.entity.User;
import com.JournalAppDemo.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try {
            userService.saveEntry(user);
            return new ResponseEntity<>(user , HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),  HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<?> getUserByName(@PathVariable String username){
        User getUser = userService.findByUserName(username);
        if(getUser != null){
            return new ResponseEntity<>(getUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
