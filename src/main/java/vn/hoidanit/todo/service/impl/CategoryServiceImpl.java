package vn.hoidanit.todo.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.hoidanit.todo.model.Category;
import vn.hoidanit.todo.repository.CategoryRepository;
import vn.hoidanit.todo.service.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(UUID id, Category category) {
        Category existingCategory = categoryRepository.findById(id).orElse(null);
        if (existingCategory != null) {
            existingCategory.setName(category.getName());
            categoryRepository.save(existingCategory);
        }
        return null;
    }

    @Override
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }

}
