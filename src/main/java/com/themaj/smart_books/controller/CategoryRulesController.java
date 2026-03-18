package com.themaj.smart_books.controller;

import com.themaj.smart_books.model.CategoryRules;
import com.themaj.smart_books.service.CategoryRulesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category-rules")
public class CategoryRulesController {
    private final CategoryRulesService categoryRulesService;


    public CategoryRulesController(CategoryRulesService categoryRulesService) {
        this.categoryRulesService = categoryRulesService;
    }
    @GetMapping
    List<CategoryRules> getCategoryRulesList() {
        return categoryRulesService.getCategoryRules();
    }
    @PostMapping
    public CategoryRules createCategoryRules(@RequestBody CategoryRules categoryRules) {
        return categoryRulesService.saveCategoryRules(categoryRules);
    }
    @GetMapping("/{id}")
    public List<CategoryRules> getCategoryRuleById(@PathVariable Long id) {
        return categoryRulesService.getCategoryRules();
    }

    @PutMapping("/{id}")
    public CategoryRules updateCategoryRulesById(@PathVariable Long id, @RequestBody CategoryRules categoryRules) {
        categoryRules.setId(id);
        return categoryRulesService.saveCategoryRules(categoryRules);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable Long id) {
        categoryRulesService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
