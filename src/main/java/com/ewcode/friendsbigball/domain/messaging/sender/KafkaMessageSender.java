package com.ewcode.friendsbigball.domain.messaging.sender;

import com.ewcode.friendsbigball.app.messaging.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageSender implements MessageSender {

    private final KafkaTemplate<String, byte[]> template;

    @Autowired
    public KafkaMessageSender(KafkaTemplate<String, byte[]> template) {
        this.template = template;
    }

    public void send(String destinationName, byte[] message) {
        template.send(destinationName, message);
    }
}
