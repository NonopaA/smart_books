package com.themaj.smart_books;

import com.themaj.smart_books.model.Category;
import com.themaj.smart_books.model.TransactionType;
import com.themaj.smart_books.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartBooksApplication {

    CategoryRepository categoryRepository;

    public SmartBooksApplication(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SmartBooksApplication.class, args);
    }
@PostConstruct
    public void init() {
        if (categoryRepository.findByName(Category.UNCATEGORIZED) == null) {
            Category category = new Category();
            category.setName("Uncategorized");
            category.setType(TransactionType.EXPENSE);
            categoryRepository.save(category);

    }
}
}
