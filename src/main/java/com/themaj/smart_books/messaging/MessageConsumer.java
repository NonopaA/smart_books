package com.themaj.smart_books.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Service
public class MessageConsumer {
    private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);
    @RabbitListener(queues = "transaction_queue")
    public void receiveMessage(String message) {
        log.info("Received message: {}", message);
    }
}
