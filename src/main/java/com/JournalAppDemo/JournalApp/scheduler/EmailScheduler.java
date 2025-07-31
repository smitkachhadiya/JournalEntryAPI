package com.JournalAppDemo.JournalApp.scheduler;

import com.JournalAppDemo.JournalApp.controller.PublicController;
import com.JournalAppDemo.JournalApp.entity.JournalEntry;
import com.JournalAppDemo.JournalApp.entity.User;
import com.JournalAppDemo.JournalApp.repository.UserRepositoryImpl;
import com.JournalAppDemo.JournalApp.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class EmailScheduler {

    @Autowired
    private PublicController publicController;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

//    @Scheduled(cron = "0 * * ? * *")
    @Scheduled(cron = "0 0 9 ? * SUN")
    public void fetchUserAndSendMail(){
        try{
            List<User> users = userRepositoryImpl.getUserForWU();
            for (User user : users){
                List<JournalEntry> journalEntries = user.getJournalEntries();
                List<String> filteredEntries = journalEntries.stream()
                        .filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                        .map(x -> x.getDate().toLocalDate()+"\n"+x.getTitle()+" : "+x.getContent()+"\n")
                        .collect(Collectors.toList());
                String body = String.join("\n", filteredEntries);
                emailService.sendEmail(user.getEmail(),"Your Journal Entries for last 7 days",body);
            }
            log.info("Mail Send...");
        } catch (Exception e){
            log.error("Error : ",e);
        }

    }
}
