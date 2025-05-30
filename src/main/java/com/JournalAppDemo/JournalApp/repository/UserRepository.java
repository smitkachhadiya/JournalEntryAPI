package com.JournalAppDemo.JournalApp.repository;


import com.JournalAppDemo.JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String username);

    User deleteByUserName(String username);
}
