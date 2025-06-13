package com.example.taskmanager;

public class Task {
    final String title;
    final String description;
    final String date;
    public Task(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    public String getDate() {
        return date;
    }
}
