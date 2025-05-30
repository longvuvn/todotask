package vn.hoidanit.todo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoidanit.todo.model.Task;
import vn.hoidanit.todo.model.User;

import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByUser(User user);
}
