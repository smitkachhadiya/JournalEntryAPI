package com.JournalAppDemo.JournalApp.repository;

import com.JournalAppDemo.JournalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepository extends MongoRepository<JournalEntry , ObjectId> {
}
