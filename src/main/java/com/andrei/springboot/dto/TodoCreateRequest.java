package com.andrei.springboot.dto;

public class TodoCreateRequest {

    private String title;
    private boolean completed;

    public TodoCreateRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
