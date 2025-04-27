package vn.hoidanit.todo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import vn.hoidanit.todo.model.*;
import vn.hoidanit.todo.service.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getByTaskId(@PathVariable UUID id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody @Validated Task task) {
        User user = taskService.getUserById(task.getUserId());
        Category category = taskService.getCategoryById(task.getCategoryId());
        task.setUser(user);
        task.setCategory(category);
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable UUID id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
    }
}
