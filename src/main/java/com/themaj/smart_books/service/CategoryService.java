package com.themaj.smart_books.service;

import com.themaj.smart_books.model.Category;
import com.themaj.smart_books.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> getACategory(Long id ) {

        return categoryRepository.findById(id);
    }

    public void deleteCategory(Long id) {
         categoryRepository.deleteById(id);
    }
}
