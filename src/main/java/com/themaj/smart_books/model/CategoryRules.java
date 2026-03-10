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

}
