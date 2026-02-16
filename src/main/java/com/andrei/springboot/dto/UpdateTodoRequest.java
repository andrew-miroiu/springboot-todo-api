package com.andrei.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class UpdateTodoRequest {

    public UpdateTodoRequest() {}

    @NotBlank
    private String title;

    @NotNull
    private Boolean completed;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Boolean isCompleted() { 
        return completed; 
    }

    public void setCompleted(Boolean completed) { 
        this.completed = completed; 
    }
}
