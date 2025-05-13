package vn.hoidanit.todo.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.hoidanit.todo.model.Category;
import vn.hoidanit.todo.model.Task;
import vn.hoidanit.todo.model.User;
import vn.hoidanit.todo.repository.*;
import vn.hoidanit.todo.service.TaskService;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Task getTaskById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(UUID id, Task task) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        existingTask.setTitle(task.getTitle());
        existingTask.setNote(task.getNote());
        existingTask.setCompleted(task.isCompleted());
        existingTask.setDeadline(task.getDeadline());
        existingTask.setStatus(task.getStatus());
        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task createTask(Task task) {
        if (task.getCategoryId() == null) {
            throw new IllegalArgumentException("Category ID must not be null");
        }

        // Lấy category từ database
        Category category = categoryRepository.findById(task.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + task.getCategoryId()));

        // Gán category vào task
        task.setCategory(category);

        // Lưu task vào database
        return taskRepository.save(task);
    }

    @Override
    public User getUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Category getCategoryById(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<Task> getTasksByUserId(UUID userId) {
        return taskRepository.findByUser_Id(userId);
    }

}