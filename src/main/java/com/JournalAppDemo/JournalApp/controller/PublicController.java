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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Public APIs")
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

    @Operation(
            summary = "User Registration",
            description = "Registers a new user and saves their credentials. On successful registration, returns the user details.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User registered successfully.",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid user data provided.", content = @Content)
            }
    )
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

    @Operation(
            summary = "User Login",
            description = "Authenticates the user and returns a JWT token upon successful login.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login successful. JWT token returned."),
                    @ApiResponse(responseCode = "400", description = "Invalid username or password.", content = @Content)
            }
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO user) {
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

    @Operation(
            summary = "Get Users with Weekly Updates Enabled",
            description = "Fetches a list of all users who have opted in for weekly updates.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of users with weekly updates enabled.",
                            content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "404", description = "No users found.", content = @Content)
            }
    )
    @GetMapping("/weeklyUpdate")
    public ResponseEntity<?> getAllWeeklyUpdateUsers(){
        List<User> all = userRepositoryImpl.getUserForWU();
        if(!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Get All Users",
            description = "Retrieves a list of all registered users in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of all users retrieved successfully.",
                            content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "404", description = "No users found in the database.", content = @Content)
            }
    )
    @GetMapping("/allusers")
    public ResponseEntity<?> getAllUser(){
        List<User> all = userService.getAll();
        if(!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Find User by Username",
            description = "Fetches user details by the given username.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User details found.",
                            content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "404", description = "User not found.", content = @Content)
            }
    )
    @GetMapping("/find/{username}")
    public ResponseEntity<?> getUserByName(@PathVariable String username){
        User getUser = userService.findByUserName(username);
        if(getUser != null){
            return new ResponseEntity<>(getUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Get All Journal Entries",
            description = "Fetches all journal entries created by users.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of journal entries retrieved.",
                            content = @Content(schema = @Schema(implementation = JournalEntry.class))),
                    @ApiResponse(responseCode = "404", description = "No journal entries found.", content = @Content)
            }
    )
    @GetMapping("/allentries")
    public ResponseEntity<?> getAll(){
        List<JournalEntry> all =  journalEntryService.getAll();
        if(!all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
