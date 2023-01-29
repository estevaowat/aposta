package com.ewcode.friendsbigball.config.messaging.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnProperty(value = "spring.kafka.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaAdminConfig {

  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.15.168:29092");
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic sentBetTopic() {
    return TopicBuilder.name("SENT_BET").partitions(10).replicas(1).build();
  }
}
