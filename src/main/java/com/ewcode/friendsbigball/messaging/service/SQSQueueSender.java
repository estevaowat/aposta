package com.ewcode.friendsbigball.messaging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SQSQueueSender {
  //  private final QueueMessagingTemplate queueMessagingTemplate;

  @Autowired
  public SQSQueueSender(
      //        QueueMessagingTemplate queueMessagingTemplate
      ) {
    //    this.queueMessagingTemplate = queueMessagingTemplate;
  }

  public void send(String destinationName, String message) {
    //  queueMessagingTemplate.send(destinationName, MessageBuilder.withPayload(message).build());
  }
}
