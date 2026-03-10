package com.themaj.smart_books.service;

import com.themaj.smart_books.dto.TransactionSummaryDto;
import com.themaj.smart_books.model.Transaction;
import com.themaj.smart_books.repository.TransactionRepository;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction save(Transaction transaction) {
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

}


