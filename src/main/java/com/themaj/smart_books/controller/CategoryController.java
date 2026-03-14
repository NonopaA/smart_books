package com.themaj.smart_books.controller;

import com.themaj.smart_books.model.Category;
import com.themaj.smart_books.service.CategoryService;
import com.themaj.smart_books.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final TransactionService  transactionService;

    public CategoryController(CategoryService categoryService, TransactionService transactionService) {
        this.categoryService = categoryService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @PutMapping("/transactions/{transactionId}/category/{categoryId}")
        public void updateCategory(@PathVariable Long transactionId,
                                   @PathVariable Long categoryId) {
    transactionService.categorizeTransaction(transactionId, categoryId);
        }

        @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
        }
    }

