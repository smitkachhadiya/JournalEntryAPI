package com.JournalAppDemo.JournalApp.entity;

// Creating A POJO (Plain Old Java Object) class

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.format.DateTimeFormatter;

@Document(collation = "journal_entries")
public class JournalEntry {
    @Id
    private ObjectId id;

    private String title;
    private String content;
    private DateTimeFormatter date;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DateTimeFormatter getDate() {
        return date;
    }

    public void setDate(DateTimeFormatter date) {
        this.date = date;
    }
}
