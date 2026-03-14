package com.themaj.smart_books.repository;

import com.themaj.smart_books.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT SUM(t.amountExclVat) FROM Transaction t WHERE t.type = 'INCOME'")
    BigDecimal sumIncome();
    @Query("SELECT SUM(t.amountExclVat) FROM Transaction t WHERE t.type = 'EXPENSES'")
    BigDecimal sumExpenses();
}
