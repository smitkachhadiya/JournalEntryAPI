package com.JournalAppDemo.JournalApp.controller;

import com.JournalAppDemo.JournalApp.entity.JournalEntry;
import com.JournalAppDemo.JournalApp.entity.User;
import com.JournalAppDemo.JournalApp.service.JournalEntryService;
import com.JournalAppDemo.JournalApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
@Slf4j
@Tag(name = "Journal APIs")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Create Journal Entry",
            description = "Creates a new journal entry for the authenticated user.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Journal entry created successfully.",
                            content = @Content(schema = @Schema(implementation = JournalEntry.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data or error while saving the journal entry.", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntryService.saveEntry(myEntry , username);
            return new ResponseEntity<>(myEntry , HttpStatus.CREATED);
        } catch (Exception e){
            log.error("Error while creating entry : {}\n",myEntry.getTitle());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Get Journal Entry by ID",
            description = "Fetches a specific journal entry by its ID for the authenticated user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Journal entry found.",
                            content = @Content(schema = @Schema(implementation = JournalEntry.class))),
                    @ApiResponse(responseCode = "404", description = "Journal entry not found.", content = @Content)
            }
    )
    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable String myId){
        ObjectId objectId = new ObjectId(myId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUserName(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(objectId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findById(objectId);
            if (journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get() , HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Get All Journal Entries for User",
            description = "Fetches all journal entries belonging to the authenticated user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of journal entries found.",
                            content = @Content(schema = @Schema(implementation = JournalEntry.class))),
                    @ApiResponse(responseCode = "404", description = "No journal entries found.", content = @Content)
            }
    )
    @GetMapping
    public ResponseEntity<?> getJournalEntryByUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User getUser = userService.findByUserName(username);
        List<JournalEntry> all = getUser.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>( username + " has no entries.", HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Update Journal Entry by ID",
            description = "Updates the content or title of an existing journal entry by ID for the authenticated user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Journal entry updated successfully.",
                            content = @Content(schema = @Schema(implementation = JournalEntry.class))),
                    @ApiResponse(responseCode = "404", description = "Journal entry not found.", content = @Content)
            }
    )
    @Transactional
    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalById(@RequestBody JournalEntry newEntry,@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUserName(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            JournalEntry old = journalEntryService.findById(myId).orElse(null);
            if(old != null){
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old , HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Delete Journal Entry by ID",
            description = "Deletes a journal entry by ID for the authenticated user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Journal entry deleted successfully."),
                    @ApiResponse(responseCode = "404", description = "Journal entry not found.", content = @Content)
            }
    )
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUserName(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if (!collect.isEmpty()){
            journalEntryService.deleteById(myId , username);
            return new ResponseEntity<>("Deleted" , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
