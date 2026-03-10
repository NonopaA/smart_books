package com.themaj.smart_books.Statementparser;

import com.themaj.smart_books.model.Transaction;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StatementParser {

        List<Transaction> parse(MultipartFile file) throws Exception;

       public String getBankName();

       public String getFileType();
}
