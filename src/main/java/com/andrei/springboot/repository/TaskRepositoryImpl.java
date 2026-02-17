package com.andrei.springboot.repository;

import com.andrei.springboot.model.Todo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepositoryCustomInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Todo> findByIdEven() {
        String jpql = "SELECT t FROM Todo t WHERE MOD(t.id, 2) = 0";
        TypedQuery<Todo> query = entityManager.createQuery(jpql, Todo.class);
        return query.getResultList();
    }
}
