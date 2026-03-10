package com.themaj.smart_books.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Date is required")
    private LocalDate date;
    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal amountExclVat;
    private BigDecimal vat;
    private BigDecimal revenue;
    private BigDecimal total;
    private BigDecimal openingBalance;
    @NotBlank(message = "Description is required")
    @Size(max = 150)
    private String description;
    private String comment;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @ManyToOne
    private Category category;

    public Transaction() {
    }

    public Transaction(Long id, LocalDate date, BigDecimal amountExclVat, BigDecimal vat, BigDecimal revenue, BigDecimal total, BigDecimal openingBalance, String description,
                       String comment, TransactionType type) {
        this.id = id;
        this.date = date;
        this.amountExclVat = amountExclVat;
        this.vat = vat;
        this.revenue = revenue;
        this.total = total;
        this.openingBalance = openingBalance;
        this.description = description;
        this.comment = comment;
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmountExclVat() {
        return amountExclVat;
    }

    public void setAmountExclVat(BigDecimal amountExclVat) {
        this.amountExclVat = amountExclVat;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", amountExclVat=" + amountExclVat +
                ", vat=" + vat +
                ", revenue=" + revenue +
                ", total=" + total +
                ", openingBalance=" + openingBalance +
                ", description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                ", type=" + type +
                '}';
    }
}
