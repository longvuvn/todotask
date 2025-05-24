package vn.hoidanit.todo.controller;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import vn.hoidanit.todo.model.ApiResponse;
import vn.hoidanit.todo.model.Category;
import vn.hoidanit.todo.service.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories() {
        List<Category> categories = this.categoryService.getAllCategories();
        ApiResponse<List<Category>> response = new ApiResponse<>(
                "success",
                "Categories retrieved successfully",
                categories,
                null,
                LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable UUID id) {
        Category category = categoryService.getCategoryById(id);
        ApiResponse<Category> responseCategory = new ApiResponse<>(
                "success",
                "Category retrieved successfully",
                category,
                null,
                LocalDateTime.now());
        return ResponseEntity.ok(responseCategory);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Category>> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        ApiResponse<Category> response = new ApiResponse<>(
                "success",
                "Category created successfully",
                createdCategory,
                null,
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> updateCategory(@PathVariable UUID id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        ApiResponse<Category> response = new ApiResponse<>(
                "success",
                "Category updated successfully",
                updatedCategory,
                null,
                LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        ApiResponse<Void> response = new ApiResponse<>(
                "success",
                "Category deleted successfully",
                null,
                null,
                LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}
