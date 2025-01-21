package com.JournalAppDemo.JournalApp.repository;

import com.JournalAppDemo.JournalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


public interface JournalEntryRepository extends MongoRepository<JournalEntry , ObjectId> {
}
