package com.andrei.springboot.controller;

import com.andrei.springboot.model.Todo;
import com.andrei.springboot.dto.TodoCreateRequest;
import com.andrei.springboot.dto.TodoRequest;
import com.andrei.springboot.dto.UpdateTodoRequest;
import com.andrei.springboot.dto.UpdateTitleRequest;
import com.andrei.springboot.service.TodoService;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAll() {
        return todoService.findAll();
    }

    @PostMapping("/users/{userId}/todos")
    public Todo createTodo(@PathVariable Long userId, @RequestBody TodoRequest request) { 

        return todoService.createTodo(userId, request.getTitle());
    }

    @PutMapping("/{id}")
    public Todo update( @PathVariable Long id, @RequestBody @Valid UpdateTodoRequest request){
        return todoService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        todoService.delete(id);
    }

    @GetMapping("/{id}")
    public Todo findById(@PathVariable Long id){
        return todoService.findById(id);
    }

    @GetMapping("/completed")
    public List<Todo> getCompleted() {
        return todoService.findCompleted();
    }

    @GetMapping("/incompleted")
    public List<Todo> getIncompleted(){
        return todoService.findIncompleted();
    }

    @GetMapping("/even")
    public List<Todo> getEvenIdTasks(){
        return todoService.findIdEven();
    }

    @PatchMapping("/{id}/toggle")
    public Todo toggle(@PathVariable Long id) {
        return todoService.toggleCompleted(id);
    }

    @PatchMapping("/{id}/changeTitle")
    public Todo changeTitle(@PathVariable Long id, @RequestBody UpdateTitleRequest titleRequest){
        return todoService.updateTitle(id, titleRequest.getTitle());
    }
}
