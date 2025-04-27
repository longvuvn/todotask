package vn.hoidanit.todo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoidanit.todo.model.Task;

import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
}

// @Modifying
// @Transactional
// @Query("UPDATE Task t SET t.user = :user, t.category = :category WHERE t.id =
// :taskId")
// void saveChange(@Param("taskId") UUID taskId, @Param("user") User user,
// @Param("category") Category category);
