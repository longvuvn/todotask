package vn.hoidanit.todo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vn.hoidanit.todo.model.*;
import vn.hoidanit.todo.service.TaskService;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getByTaskId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable UUID id, @RequestBody Task task) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.updateTask(id, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
