package com.themaj.smart_books.dto;

import java.math.BigDecimal;

public record TransactionSummaryDTO(
        BigDecimal income,
        BigDecimal expenses,
        BigDecimal balance
) {
}
