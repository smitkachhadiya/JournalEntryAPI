package com.JournalAppDemo.JournalApp.controller;

import com.JournalAppDemo.JournalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/_journal")    // add mapping to class ex: https://journal/...
public class JournalEntryControllerV2 {

//    private Map<ObjectId, JournalEntry> journalEntries = new HashMap<>();
//
//     // writing specific end-points as methods
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping
//    public String  createEntry(@RequestBody JournalEntry myEntry){
//        journalEntries.put(myEntry.getId(), myEntry);
//        return "record Inserted";
//    }
//
//    @GetMapping("/id/{myId}")
//    public JournalEntry getJournalEntryById(@PathVariable Long myId){
//        return journalEntries.get(myId);
//    }
//
//    @DeleteMapping("/id/{myId}")
//    public JournalEntry deleteJournalEntry(@PathVariable Long myId){
//        return journalEntries.remove(myId);
//    }
//
//    @PutMapping("/id/{myId}")
//    public String  updateJournalEntry(@PathVariable ObjectId myId , @RequestBody JournalEntry myEntry){
//        journalEntries.put(myId,myEntry);
//        return "Record Updated";
//    }
}
