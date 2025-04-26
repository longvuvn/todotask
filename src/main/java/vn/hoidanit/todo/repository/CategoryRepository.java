package vn.hoidanit.todo.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hoidanit.todo.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
