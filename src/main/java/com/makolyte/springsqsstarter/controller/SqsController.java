package com.makolyte.springsqsstarter.controller;

import com.makolyte.springsqsstarter.model.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sqs")
public class SqsController {
    private static final Logger LOG = LoggerFactory.getLogger(SqsController.class);

    private final QueueMessagingTemplate queueMessagingTemplate;

    public SqsController(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    @PostMapping("/quotes")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendQuote(@RequestBody @Valid Quote quote) {
        LOG.info("Sending quote {} to SQS", quote);
        this.queueMessagingTemplate.convertAndSend("QuoteQueue", quote);
    }
}
