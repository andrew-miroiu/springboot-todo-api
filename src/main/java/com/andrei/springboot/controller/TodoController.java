package com.andrei.springboot.controller;

import com.andrei.springboot.model.Todo;
import com.andrei.springboot.dto.TodoCreateRequest;
import com.andrei.springboot.dto.UpdateTodoRequest;
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

    @PostMapping
    public Todo create(@RequestBody TodoCreateRequest request) {
        return todoService.create(request);
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
}
