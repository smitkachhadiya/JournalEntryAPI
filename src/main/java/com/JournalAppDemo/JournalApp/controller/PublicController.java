package com.JournalAppDemo.JournalApp.controller;

import com.JournalAppDemo.JournalApp.dto.UserDTO;
import com.JournalAppDemo.JournalApp.entity.JournalEntry;
import com.JournalAppDemo.JournalApp.entity.User;
import com.JournalAppDemo.JournalApp.repository.UserRepositoryImpl;
import com.JournalAppDemo.JournalApp.service.JournalEntryService;
import com.JournalAppDemo.JournalApp.service.UserDetailServiceImplementation;
import com.JournalAppDemo.JournalApp.service.UserService;
import com.JournalAppDemo.JournalApp.utilis.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImplementation userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody UserDTO user){
        try {
            User newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setUserName(user.getUserName());
            newUser.setPassword(user.getPassword());
            newUser.setWeeklyUpdate(user.isWeeklyUpdate());
            userService.saveNewUser(newUser);
            return new ResponseEntity<>(user , HttpStatus.CREATED);
        } catch (Exception e){
//            logger.error("Error Occurred while creating user : {} ",user.getUserName());
            log.error("Error Occurred while creating user : {} \n{}",user.getUserName(),e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/weeklyUpdate")
    public ResponseEntity<?> getAllWeeklyUpdateUsers(){
        List<User> all = userRepositoryImpl.getUserForWU();
        if(!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    @Operation(summary = "Get all journal entries of a user")
    public ResponseEntity<?> getAll(){
        List<JournalEntry> all =  journalEntryService.getAll();
        if(!all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
