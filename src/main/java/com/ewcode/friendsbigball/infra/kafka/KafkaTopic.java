package com.ewcode.friendsbigball.infra.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {
    public static final String BOOK_BET_TOPIC = "book_bet";

    @Bean
    public NewTopic bookBetTopic() {
        return TopicBuilder.name(BOOK_BET_TOPIC).build();
    }
}
