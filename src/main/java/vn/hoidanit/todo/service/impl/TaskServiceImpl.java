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
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

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
        // Kiểm tra nếu userId không được cung cấp
        if (task.getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        // Kiểm tra nếu categoryId không được cung cấp
        if (task.getCategoryId() == null) {
            throw new IllegalArgumentException("Category ID must not be null");
        }

        // Lấy thông tin User từ cơ sở dữ liệu
        User user = userRepository.findById(task.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + task.getUserId()));

        // Lấy thông tin Category từ cơ sở dữ liệu
        Category category = categoryRepository.findById(task.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + task.getCategoryId()));

        // Gán User và Category vào Task
        task.setUser(user);
        task.setCategory(category);

        // Lưu Task vào cơ sở dữ liệu
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

}