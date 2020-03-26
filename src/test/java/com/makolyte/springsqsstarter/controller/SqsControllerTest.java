package com.makolyte.springsqsstarter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makolyte.springsqsstarter.model.Quote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SqsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private QueueMessagingTemplate queueMessagingTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new SqsController(queueMessagingTemplate)).build();
    }

    @Test
    public void sendQuoteToSqs() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Quote q = new Quote(
                "With the new day comes new strength and new thoughts.",
                "Eleanor Roosevelt"
        );
        String json = mapper.writeValueAsString(q);

        mockMvc.perform(post("/sqs/quotes")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}