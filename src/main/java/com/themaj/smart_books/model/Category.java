package com.themaj.smart_books.model;

import jakarta.persistence.*;

@Entity
public class Category {
    public static final String UNCATEGORIZED = "Uncategorized";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public Category() {
    }

    public Category(String name, TransactionType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    public Long getId() {
        return id;
    }
}
