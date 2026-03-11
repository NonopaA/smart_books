package com.themaj.smart_books.controller;

import com.themaj.smart_books.model.CategoryRules;
import com.themaj.smart_books.service.CategoryRulesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
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

}
