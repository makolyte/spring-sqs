package com.makolyte.springsqsstarter.service;

import com.makolyte.springsqsstarter.dto.Quote;

public interface SqsService {
    void saveQuote(Quote incomingQuote,
                   String messageId,
                   String approximateFirstReceiveTimestamp);
}
