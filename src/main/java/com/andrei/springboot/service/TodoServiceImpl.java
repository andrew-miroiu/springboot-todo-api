package com.andrei.springboot.service;

import com.andrei.springboot.model.Todo;
import com.andrei.springboot.dto.TodoCreateRequest;
import com.andrei.springboot.dto.UpdateTodoRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final List<Todo> todos = new ArrayList<>();
    private long nextId = 1;

    public TodoServiceImpl(){
        todos.add(new Todo(nextId++, "First task", true));
        todos.add(new Todo(nextId++, "Second task", true));
        todos.add(new Todo(nextId++, "Third task", true));
    }
    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(todos);
    }

    @Override
    public Todo create(TodoCreateRequest request) {
        Todo todo = new Todo(nextId++, request.getTitle(), request.isCompleted());
        todos.add(todo);
        return todo;
    }

    @Override
    public Todo update(Long id, UpdateTodoRequest request){
        for (Todo todo : todos) {
            if (todo.getId().equals(id)) {

            todo.setTitle(request.getTitle());
            todo.setCompleted(request.isCompleted());

            return todo;
        }
    }

        throw new RuntimeException("Todo not found with id " + id);
    }

    @Override
    public void delete(Long id){
        boolean removed = todos.removeIf(todo -> todo.getId().equals(id));

        if(!removed){
            throw new RuntimeException("Todo not found with id " + id);
        }
    }

    @Override
    public Todo findById(Long id){
        for(Todo todo: todos){
            if(todo.getId().equals(id)){
                return todo;
            }
        }

        throw new RuntimeException("Todo not found with id " + id);
    }
}
