package com.makolyte.springsqsstarter.service;

import com.makolyte.springsqsstarter.dto.IncomingQuote;
import com.makolyte.springsqsstarter.entity.QuoteEntity;
import com.makolyte.springsqsstarter.repository.QuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SqsServiceImplTest {

    private SqsService sqsServiceImpl;

    @Mock
    private QuoteRepository quoteRepository;

    @BeforeEach
    public void setup() {
        sqsServiceImpl = new SqsServiceImpl(quoteRepository);
    }

    @Test
    public void saveQuote_quoteExists_itIsNotSaved() {
        String awsMessageId = "12345";
        when(quoteRepository.existsByAwsMessageId(awsMessageId)).thenReturn(true);
        IncomingQuote incomingQuote = mock(IncomingQuote.class);

        sqsServiceImpl.saveQuote(
                incomingQuote,
                awsMessageId,
                "1585818038");

        verify(quoteRepository, never()).save(any(QuoteEntity.class));
    }

    @Test
    public void saveQuote_quoteDoesNotExist_itIsSaved() {
        String awsMessageId = "12345";
        String receivedTimestamp = "1585818038";
        when(quoteRepository.existsByAwsMessageId(awsMessageId)).thenReturn(false);
        IncomingQuote incomingQuote = new IncomingQuote(
                "The greatest glory in living lies not in never falling, " +
                        "but in rising every time we fall.",
                "Nelson Mandela"
        );

        sqsServiceImpl.saveQuote(
                incomingQuote,
                awsMessageId,
                receivedTimestamp);

        ArgumentCaptor<QuoteEntity> quoteEntityCaptor = ArgumentCaptor.forClass(QuoteEntity.class);
        verify(quoteRepository).save(quoteEntityCaptor.capture());
        QuoteEntity quoteEntity = quoteEntityCaptor.getValue();
        assertEquals(incomingQuote.getText(), quoteEntity.getText());
        assertEquals(incomingQuote.getAuthor(), quoteEntity.getAuthor());
        assertEquals(awsMessageId, quoteEntity.getAwsMessageId());
        assertEquals(Long.parseLong(receivedTimestamp), quoteEntity.getDateReceived().toEpochMilli());
    }
}