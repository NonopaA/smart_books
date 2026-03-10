package com.themaj.smart_books.controller;

import com.themaj.smart_books.dto.TransactionSummaryDto;
import com.themaj.smart_books.model.Transaction;
import com.themaj.smart_books.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService statementService;

    public TransactionController(TransactionService transactionService) {
        this.statementService = transactionService;
    }

    @PostMapping
    public Transaction create(@Valid @RequestBody Transaction transaction) {
        return statementService.save(transaction);
    }
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return statementService.getAllTransactions();
    }

    @GetMapping("/summary")
    public TransactionSummaryDto getSummary() {
        return statementService.getSummary();
    }


}
