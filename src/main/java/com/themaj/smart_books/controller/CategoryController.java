package com.themaj.smart_books.controller;

import com.themaj.smart_books.model.Category;
import com.themaj.smart_books.service.CategoryService;
import com.themaj.smart_books.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    private TransactionService  transactionService;

    public CategoryController(CategoryService categoryService, TransactionService transactionService) {
        this.categoryService = categoryService;
        this.transactionService = transactionService;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @PutMapping("/transactions/{transactionId}/category/{categoryId}")
        public void updateCategory(@PathVariable Long transactionId,
                                   @PathVariable Long categoryId) {
    transactionService.categorizeTransaction(transactionId, categoryId);
        }
    }

