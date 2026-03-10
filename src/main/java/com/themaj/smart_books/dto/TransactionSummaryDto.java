package com.themaj.smart_books.dto;

import java.math.BigDecimal;

public class TransactionSummaryDto {
    private BigDecimal income;
    private BigDecimal expenses;
    private BigDecimal balance;
    public TransactionSummaryDto(BigDecimal income, BigDecimal expenses, BigDecimal balance) {
        this.income = income;
        this.expenses = expenses;
        this.balance = balance;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
