package vn.hoidanit.todo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vn.hoidanit.todo.model.*;
import vn.hoidanit.todo.repository.UserRepository;
import vn.hoidanit.todo.service.TaskService;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final UserRepository userRepository;

    @GetMapping("/tasks/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.status(HttpStatus.OK)
                .body(tasks);
    }

    @GetMapping("/tasks")
    public List<Task> getTasksForUser(Authentication authentication) {
        String username = authentication.getName(); // Lấy username từ token
        return taskService.findTasksByUsername(username); // Chỉ trả về task của user này
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getByTaskId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.getTaskById(id));
    }

    @PostMapping("/create_tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        Task savedTask = taskService.createTask(task, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable UUID id, @RequestBody Task task) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.updateTask(id, task));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
