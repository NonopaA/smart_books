package com.themaj.smart_books.repository;

import com.themaj.smart_books.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    BigDecimal sumIncome();

    BigDecimal sumExpenses();
}
