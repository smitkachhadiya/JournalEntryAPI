package com.JournalAppDemo.JournalApp.controller;

import com.JournalAppDemo.JournalApp.entity.JournalEntry;
import com.JournalAppDemo.JournalApp.entity.User;
import com.JournalAppDemo.JournalApp.service.JournalEntryService;
import com.JournalAppDemo.JournalApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @PostMapping("/create-user")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try {
            userService.saveNewUser(user);
            return new ResponseEntity<>(user , HttpStatus.CREATED);
        } catch (Exception e){
//            logger.error("Error Occurred while creating user : {} ",user.getUserName());
            log.error("Error Occurred while creating user : {} \n{}",user.getUserName(),e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/allusers")
    public ResponseEntity<?> getAllUser(){
        List<User> all = userService.getAll();
        if(!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<?> getUserByName(@PathVariable String username){
        User getUser = userService.findByUserName(username);
        if(getUser != null){
            return new ResponseEntity<>(getUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/allentries")
    public ResponseEntity<?> getAll(){
        List<JournalEntry> all =  journalEntryService.getAll();
        if(!all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
