package com.ewcode.friendsbigball.infra.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String producerBootstrapAddress;

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String consumerBootstrapAddress;

    @Bean
    public Map<String, Object> producerByteArrayProperties() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerBootstrapAddress);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
        return configs;
    }

    @Bean
    public ProducerFactory<String, Byte[]> kafkaProducer() {
        return new DefaultKafkaProducerFactory<>(producerByteArrayProperties());
    }

    @Bean
    public Map<String, Object> consumerByteArrayProperties() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerBootstrapAddress);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
        return configs;
    }

    @Bean
    public KafkaTemplate<String, Byte[]> bytesTemplate() {
        return new KafkaTemplate<>(kafkaProducer(), consumerByteArrayProperties());
    }

    @Bean
    public ConsumerFactory<String, Byte[]> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerByteArrayProperties());
    }
}
