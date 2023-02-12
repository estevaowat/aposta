package com.ewcode.friendsbigball.messaging.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageSender {

    private final KafkaTemplate<String, byte[]> template;


    @Autowired
    public KafkaMessageSender(KafkaTemplate<String, byte[]> template

    ) {
        this.template = template;

    }

    public void send(String destinationName, byte[] message) {
      template.send(destinationName, message);
    }


}
