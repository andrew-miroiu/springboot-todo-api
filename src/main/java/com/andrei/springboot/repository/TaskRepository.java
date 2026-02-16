package com.andrei.springboot.repository;

import com.andrei.springboot.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Todo, Long> {
}
