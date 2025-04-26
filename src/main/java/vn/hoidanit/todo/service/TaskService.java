package vn.hoidanit.todo.service;

import java.util.List;
import java.util.UUID;

import vn.hoidanit.todo.model.Task;

public interface TaskService {
    List<Task> getAllTasks();

    Task getTaskById(UUID id);

    Task createTask(Task task, UUID userId, UUID categoryId);

    Task updateTask(UUID id, Task task);

    void deleteTask(UUID id);
}
