package vn.hoidanit.todo.service;

import java.util.List;
import java.util.UUID;

import vn.hoidanit.todo.model.*;

public interface TaskService {
    List<Task> getAllTasks();

    Task getTaskById(UUID id);

    Task createTask(Task task);

    User getUserById(UUID userId);

    Category getCategoryById(UUID categoryId);

    Task updateTask(UUID id, Task task);

    void deleteTask(UUID id);

    List<Task> getTasksByUserId(UUID userId);

}
