package com.themaj.smart_books.controller;

import com.themaj.smart_books.messaging.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private MessageProducer messageProducer;

    @GetMapping("/send")
    public String message() {

        return "Message sent";
    }
}
