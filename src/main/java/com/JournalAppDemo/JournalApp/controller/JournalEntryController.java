package com.JournalAppDemo.JournalApp.controller;

import com.JournalAppDemo.JournalApp.entity.JournalEntry;
import com.JournalAppDemo.JournalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public String createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return "Record Inserted : " + myEntry.getTitle();
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null);
    }

    @PutMapping("id/{myId}")
    public String updateJournalById(@RequestBody JournalEntry newEntry,@PathVariable ObjectId myId){
        JournalEntry old = journalEntryService.findById(myId).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return ("Record Updated : " + old.getTitle());
    }

    @DeleteMapping("id/{myId}")
    public String deleteJournalEntryById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return " Record Deleted ";
    }

}
