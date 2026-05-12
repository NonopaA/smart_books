package com.themaj.smart_books.statementparser;

import com.themaj.smart_books.model.Transaction;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StatementParser {
//statement parser reads the pdf/csv bank statement and convert it into a Transaction object
        List<Transaction> parse(MultipartFile file) throws Exception;

       public String getBankName();

       public String getFileType();
}
