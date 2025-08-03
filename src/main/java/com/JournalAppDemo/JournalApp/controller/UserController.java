package com.JournalAppDemo.JournalApp.controller;

import com.JournalAppDemo.JournalApp.entity.User;
import com.JournalAppDemo.JournalApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User APIs")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Update User Profile",
            description = "Updates the authenticated user's profile details like username, password, email, and weekly update preferences.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User profile updated successfully.",
                            content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid.", content = @Content),
                    @ApiResponse(responseCode = "404", description = "User not found in database.", content = @Content)
            }
    )
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userInDb.setEmail(user.getEmail());
        userInDb.setWeeklyUpdate(user.getWeeklyUpdate());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(userInDb, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User Account",
            description = "Deletes the authenticated user's account permanently from the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User account deleted successfully."),
                    @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid.", content = @Content),
                    @ApiResponse(responseCode = "404", description = "User not found in database.", content = @Content)
            }
    )
    @DeleteMapping
    public ResponseEntity<?> deleteUSer(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userService.deleteByUserName(userName);
        return new ResponseEntity<>(userName + " Deleted ...", HttpStatus.OK);
    }

}
