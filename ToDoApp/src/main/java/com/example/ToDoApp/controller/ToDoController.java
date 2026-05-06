package com.example.ToDoApp.controller;

import com.example.ToDoApp.entity.ToDoEntity;
import com.example.ToDoApp.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoController {
    private ToDoService service;

    public ToDoController(ToDoService service) {
        System.out.println("hi");
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ToDoEntity> create(@RequestBody ToDoEntity toDoEntity) {
        System.out.println("incoming" + toDoEntity.getId());
        return new ResponseEntity<>(service.create(toDoEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ToDoEntity> getAll(@RequestParam(required = false) Boolean completed) {
        return service.getAll(completed);
    }

    @GetMapping("/{id}")
    public ToDoEntity getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ToDoEntity update(@PathVariable Long id, @RequestBody ToDoEntity toDoEntity) {
        return service.update(id, toDoEntity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}")
    public ToDoEntity markAsCompleted(@PathVariable Long id) {
        return service.markAsCompleted(id);
    }
}
