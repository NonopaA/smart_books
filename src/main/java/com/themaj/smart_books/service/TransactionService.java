package com.themaj.smart_books.service;

import com.themaj.smart_books.dto.TransactionSummaryDto;
import com.themaj.smart_books.model.Category;
import com.themaj.smart_books.model.CategoryRules;
import com.themaj.smart_books.model.Transaction;
import com.themaj.smart_books.repository.CategoryRepository;
import com.themaj.smart_books.repository.CategoryRulesRepository;
import com.themaj.smart_books.repository.TransactionRepository;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    private final CategoryRulesRepository categoryRulesRepository;

    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository,
    CategoryRulesRepository categoryRulesRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.categoryRulesRepository = categoryRulesRepository;
    }

    public Transaction save(Transaction transaction) {
        if (transaction.getCategory() != null) {
            Long categoryId = transaction.getCategory().getId();
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            transaction.setCategory(category);
        }
        return transactionRepository.save(transaction);
    }

    public void saveAll(List<Transaction> transactions) {

        transactionRepository.saveAll(transactions);
    }
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public TransactionSummaryDto getSummary() {
        List<Transaction> transactions = transactionRepository.findAll();
            BigDecimal income = BigDecimal.ZERO;
            BigDecimal expenses = BigDecimal.ZERO;

            for (Transaction trans : transactions) {

                if (trans.getRevenue() != null && trans.getRevenue().compareTo(BigDecimal.ZERO) > 0) {
                    income = income.add(trans.getRevenue());
                }
                if (trans.getTotal() != null && trans.getTotal().compareTo(BigDecimal.ZERO) > 0) {
                    expenses = expenses.add(trans.getTotal());
                }
            }
            BigDecimal balance = income.subtract(expenses);
        return new TransactionSummaryDto(income, expenses, balance);
    }
    public void categorizeTransaction(Long transactionId, Long categoryId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        transaction.setCategory(category);
        transactionRepository.save(transaction);
    }

    public void categorizeTransaction() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<CategoryRules> categoryRules = categoryRulesRepository.findAll();

        for (Transaction transaction : transactions) {
            if (transaction.getCategory() !=null) {
                continue;
            }
            for (CategoryRules rules : categoryRules) {
                if (transaction.getDescription().toLowerCase()
                        .contains(rules.getKeyword().toLowerCase()));

                transaction.setCategory(rules.getCategory());
                transactionRepository.save(transaction);
            }
        }
        
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }
}


