package com.themaj.smart_books;

import com.themaj.smart_books.model.CategoryRules;
import com.themaj.smart_books.service.CategoryRulesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRulesTest {
    @Autowired
    private CategoryRulesService categoryRulesService;

    @Test
    void testCategoryRules() {

        List<CategoryRules> category = categoryRulesService.getCategoryRules();
    }

}
