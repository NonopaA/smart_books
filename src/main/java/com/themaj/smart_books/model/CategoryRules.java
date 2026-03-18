package com.themaj.smart_books.model;

import jakarta.persistence.*;
import com.themaj.smart_books.model.Category;


@Entity
public class CategoryRules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String keyword;
    @ManyToOne
    private Category category;

    public CategoryRules() {
    }

    public CategoryRules(String keyword, Category category) {
        this.keyword = keyword;
        this.category = category;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategoryRules{" +
                "keyword='" + keyword + '\'' +
                ", category=" + category +
                '}';
    }

    public void setId(Long id) {
        this.id = id;

    }
}
