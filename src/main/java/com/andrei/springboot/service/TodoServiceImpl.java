package com.andrei.springboot.service;

import com.andrei.springboot.dto.TodoCreateRequest;
import com.andrei.springboot.dto.UpdateTodoRequest;
import com.andrei.springboot.model.Todo;
import com.andrei.springboot.repository.TaskRepository;
import com.andrei.springboot.repository.UserRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import com.andrei.springboot.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import com.andrei.springboot.security.CustomUserDetails;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public TodoServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Todo> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Todo createTodo(Long userId, String title) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setCompleted(false);
        todo.setUser(user);

        return taskRepository.save(todo);
    }

    @Override
    public Todo createTodoJwt(String title) {

        CustomUserDetails userDetails =(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long userId = userDetails.getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setCompleted(false);
        todo.setUser(user);

        return taskRepository.save(todo);
    }


    @Override
    public Todo update(Long id, UpdateTodoRequest request) {
        Optional<Todo> optionalTodo = taskRepository.findById(id);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setTitle(request.getTitle());
            todo.setCompleted(request.isCompleted());
            return taskRepository.save(todo);
        }
        throw new RuntimeException("Todo not found with id " + id);
    }

    @Override
    @Transactional
    public Todo updateTitle(Long id, String title){
        Todo todo = taskRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Todo not found with id " + id));

        todo.setTitle(title);
        return todo;
    }

    @Override
    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Todo not found with id " + id);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public Todo findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id " + id));
    }

    @Override
    public List<Todo> findCompleted(){
        return taskRepository.findCompletedTodos();
    }

   /* @Override
    public List<Todo> findCompleted() {
        // folosim JPQL
        String jpql = "SELECT t FROM Todo t WHERE t.completed = true";
        TypedQuery<Todo> query = entityManager.createQuery(jpql, Todo.class);
        return query.getResultList();
    } */

    @Override
    public List<Todo> findIncompleted(){
        return taskRepository.findIncompletedTodos();
    }

    @Override
    public List<Todo> findIdEven(){
        return taskRepository.findByIdEven();
    }

    @Transactional
    public Todo toggleCompleted(Long id) {
        Todo todo = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        todo.setCompleted(!todo.isCompleted());

        return todo;
    }

}
