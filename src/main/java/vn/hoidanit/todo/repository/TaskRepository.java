package vn.hoidanit.todo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoidanit.todo.model.Task;

import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
}
