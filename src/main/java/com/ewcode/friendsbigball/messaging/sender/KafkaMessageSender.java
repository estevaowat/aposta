package com.ewcode.friendsbigball.messaging.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageSender {

  private final KafkaTemplate<String, byte[]> template;
  // private final ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;

  @Autowired
  public KafkaMessageSender(KafkaTemplate<String, byte[]> template
                            //      ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate
      ) {
    this.template = template;
    // this.replyingKafkaTemplate = replyingKafkaTemplate;
  }

  public void send(String destinationName, byte[] message) {
    template.send(destinationName, message);
  }

  //  public CompletableFuture<SendResult<String, String>> sendAndReceive(
  //          String destinationName, String message) {
  //    ProducerRecord<String, String> producerRecord = new ProducerRecord<>(destinationName,
  // message);
  //    return replyingKafkaTemplate.sendAndReceive(producerRecord).getSendFuture();
  //  }
}
