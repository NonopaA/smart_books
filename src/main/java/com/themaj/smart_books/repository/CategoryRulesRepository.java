package com.themaj.smart_books.repository;

import com.themaj.smart_books.model.CategoryRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRulesRepository extends JpaRepository<CategoryRules, Long> {
}
