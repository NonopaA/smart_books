package com.themaj.smart_books.controller;

import com.themaj.smart_books.dto.TransactionSummaryDto;
import com.themaj.smart_books.service.StatementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final StatementService statementService;

    public ReportController(StatementService statementService) {
        this.statementService = statementService;
    }

    @GetMapping("/summary")
    public TransactionSummaryDto getSummary() {
        return statementService.getSummary();
    }
}
