package vn.hoidanit.todo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.hoidanit.todo.model.Task;
import vn.hoidanit.todo.model.*;

import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.user = :user, t.category = :category WHERE t.id = :taskId")
    void saveChange(@Param("taskId") UUID taskId, @Param("user") User user, @Param("category") Category category);
}
