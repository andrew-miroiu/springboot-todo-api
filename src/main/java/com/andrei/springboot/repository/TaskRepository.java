package com.andrei.springboot.repository;

import com.andrei.springboot.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.andrei.springboot.repository.TaskRepositoryCustomInterface;


import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Todo, Long>, TaskRepositoryCustomInterface {

    @Query("SELECT t FROM Todo t WHERE t.completed = true")
    List<Todo> findCompletedTodos();

    @Query("SELECT t FROM Todo t WHERE t.completed = false")
    List<Todo> findIncompletedTodos();

    List<Todo> findByIdEven();
}
