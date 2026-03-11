package com.themaj.smart_books.service;

import com.themaj.smart_books.model.CategoryRules;
import com.themaj.smart_books.repository.CategoryRulesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryRulesService {

    private final CategoryRulesRepository categoryRulesRepository;


    public CategoryRulesService(CategoryRulesRepository categoryRulesRepository) {
        this.categoryRulesRepository = categoryRulesRepository;
    }

    public List<CategoryRules> getCategoryRules() {
        return categoryRulesRepository.findAll();
    }

    public CategoryRules saveCategoryRules(CategoryRules categoryRules) {
        return categoryRulesRepository.save(categoryRules);
    }
}
