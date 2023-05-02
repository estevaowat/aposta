package com.ewcode.friendsbigball.domain.messaging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SQSQueueSender {

    @Autowired
    public SQSQueueSender() {
        //
    }

    public void send(String destinationName, String message) {
        //
    }
}
