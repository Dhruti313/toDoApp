package com.example.ToDoApp.service;

import com.example.ToDoApp.entity.ToDoEntity;
import com.example.ToDoApp.exception.ResourceNotFoundException;
import com.example.ToDoApp.repository.ToDoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ToDoService {
    private ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }

    //method to create
    @CacheEvict(value = {"todo", "todos"}, allEntries = true)
    public ToDoEntity create(ToDoEntity toDoEntity){
        return toDoRepository.save(toDoEntity);
    }

    //method to find by completed
    @Cacheable("todos")
    public List<ToDoEntity> getAll(Boolean completed){
        if(completed != null){
            return toDoRepository.findByCompleted(completed);
        }
        return toDoRepository.findAll();
    }

    //method to find by id
    @Cacheable(value = "todo", key = "#id")
    public ToDoEntity getById(Long id){
       return toDoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
    }

    //method to delete
    @CacheEvict(value = {"todo", "todos"}, allEntries = true)
    public void delete(Long id){
        ToDoEntity todo = getById(id);
        toDoRepository.delete(todo);
    }

    //method to update
    @CacheEvict(value = {"todo", "todos"}, allEntries = true)
    public ToDoEntity update(Long id, ToDoEntity updated){
        ToDoEntity todo = getById(id);
        todo.setTitle(updated.getTitle());
        todo.setDescription(updated.getDescription());
        todo.setCompleted(updated.isCompleted());
        return toDoRepository.save(todo);
    }

    //method to mark as completed
    @CacheEvict(value = {"todo", "todos"}, allEntries = true)
    public ToDoEntity markAsCompleted(Long id){
        ToDoEntity todo = getById(id);
        todo.setCompleted(true);
        return toDoRepository.save(todo);
    }
}
