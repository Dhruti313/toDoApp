package com.example.ToDoApp.repository;

import com.example.ToDoApp.entity.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//second class
public interface ToDoRepository  extends JpaRepository<ToDoEntity, Long> {
    List<ToDoEntity> findByCompleted(Boolean completed);
}
