package com.makolyte.springsqsstarter.controller;

import com.makolyte.springsqsstarter.dto.Quote;
import com.makolyte.springsqsstarter.service.SqsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sqs")
public class SqsController {
    private static final Logger LOG = LoggerFactory.getLogger(SqsController.class);
    public static final String QUOTE_QUEUE = "QuoteQueue";

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final SqsService sqsService;

    public SqsController(
            QueueMessagingTemplate queueMessagingTemplate,
            SqsService sqsService) {
        this.queueMessagingTemplate = queueMessagingTemplate;
        this.sqsService = sqsService;
    }

    @PostMapping("/quotes")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendQuote(@RequestBody @Valid Quote quote) {
        LOG.info("Sending quote {} to SQS", quote);
        this.queueMessagingTemplate.convertAndSend(QUOTE_QUEUE, quote);
    }

    @SqsListener(QUOTE_QUEUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void receiveQuote(@Valid Quote quote,
                             @Header("MessageId") String messageId,
                             @Header("ApproximateFirstReceiveTimestamp") String approximateFirstReceiveTimestamp) {
        LOG.info("Received quote {} with messageId {}", quote, messageId);
        sqsService.saveQuote(quote, messageId, approximateFirstReceiveTimestamp);
    }
}
