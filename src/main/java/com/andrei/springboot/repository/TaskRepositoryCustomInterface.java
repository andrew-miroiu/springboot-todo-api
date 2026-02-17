package com.andrei.springboot.repository;

import com.andrei.springboot.model.Todo;
import java.util.List;

public interface TaskRepositoryCustomInterface {
    List<Todo> findByIdEven();
}
