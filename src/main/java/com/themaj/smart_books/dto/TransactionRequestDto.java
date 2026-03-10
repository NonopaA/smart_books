package com.themaj.smart_books.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionRequestDto {
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
}
