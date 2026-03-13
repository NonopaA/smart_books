package com.themaj.smart_books.controller;

import com.themaj.smart_books.service.StatementService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class UploadController {
    private final StatementService statementService;

    public UploadController(StatementService statementService) {
        this.statementService = statementService;
    }

    private static final Logger log = LoggerFactory.getLogger(UploadController.class);

    @PostMapping(value = "/upload-file",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadStatement(@RequestParam("file") MultipartFile file,
                                                  @RequestParam("bank") String bank,
                                                  @RequestParam("fileType") String fileType) throws Exception {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is missing");
        }
        statementService.process(file, bank, fileType );
        return ResponseEntity.ok("Statement uploaded successfully");
    }
}