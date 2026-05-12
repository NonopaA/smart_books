package com.themaj.smart_books.dto;

import java.math.BigDecimal;

public record TransactionSummaryDto(
        BigDecimal income,
        BigDecimal expenses,
        BigDecimal balance
) {
}
