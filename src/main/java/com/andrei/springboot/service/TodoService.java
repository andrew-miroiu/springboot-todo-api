package com.andrei.springboot.service;

import com.andrei.springboot.model.Todo;
import com.andrei.springboot.dto.TodoCreateRequest;
import com.andrei.springboot.dto.UpdateTodoRequest;

import java.util.List;

public interface TodoService {
    List<Todo> findAll();
    Todo createTodo(Long userId, String title);
    Todo update(Long id,UpdateTodoRequest updateRequest);
    void delete(Long id);
    Todo findById(Long id);
    List<Todo> findCompleted();
    List<Todo> findIncompleted();
    List<Todo> findIdEven();
    Todo toggleCompleted(Long id);
    Todo updateTitle(Long id, String Title);
}
