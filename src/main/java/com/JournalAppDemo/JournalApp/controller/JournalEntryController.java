package com.JournalAppDemo.JournalApp.controller;

import com.JournalAppDemo.JournalApp.entity.JournalEntry;
import com.JournalAppDemo.JournalApp.entity.User;
import com.JournalAppDemo.JournalApp.service.JournalEntryService;
import com.JournalAppDemo.JournalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @PostMapping("{username}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry , @PathVariable String username){
        try {
            journalEntryService.saveEntry(myEntry , username);
            return new ResponseEntity<>(myEntry , HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<JournalEntry> all =  journalEntryService.getAll();
        if(!all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if (journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get() , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("user/{username}")
    public ResponseEntity<?> getJournalEntryByUser(@PathVariable String username){
        User getUser = userService.findByUserName(username);
        if (getUser != null){
            List<JournalEntry> all = getUser.getJournalEntries();
            if(!all.isEmpty()){
                return new ResponseEntity<>(all, HttpStatus.OK);
            }
            return new ResponseEntity<>( username + " has no entries.", HttpStatus.OK);
        }
        return new ResponseEntity<>("user not found",HttpStatus.NOT_FOUND);
    }

    @Transactional
    @PutMapping("id/{username}/{myId}")
    public ResponseEntity<?> updateJournalById(@RequestBody JournalEntry newEntry,@PathVariable String username,@PathVariable ObjectId myId){
        JournalEntry old = journalEntryService.findById(myId).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{username}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable String username ,@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if (journalEntry.isPresent()){
            journalEntryService.deleteById(myId , username);
            return new ResponseEntity<>("Deleted" , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
