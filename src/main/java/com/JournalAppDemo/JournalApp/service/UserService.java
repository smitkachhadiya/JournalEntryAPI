package com.JournalAppDemo.JournalApp.service;

import com.JournalAppDemo.JournalApp.entity.JournalEntry;
import com.JournalAppDemo.JournalApp.entity.User;
import com.JournalAppDemo.JournalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void saveEntry(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

}
