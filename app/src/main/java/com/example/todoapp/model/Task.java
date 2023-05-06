package com.example.todoapp.model;

public class Task {
    private String title, description, date;
    private boolean done;

    public Task() {}
    public Task(String title, String description, String date, boolean done) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.done = done;
    }


    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
