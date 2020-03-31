package com.makolyte.springsqsstarter.service;

import com.makolyte.springsqsstarter.dto.IncomingQuote;

public interface SqsService {
    void saveQuote(IncomingQuote incomingQuote,
                   String messageId,
                   String approximateFirstReceiveTimestamp);
}
