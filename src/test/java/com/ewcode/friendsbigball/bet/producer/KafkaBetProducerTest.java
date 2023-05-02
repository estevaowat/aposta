package com.ewcode.friendsbigball.bet.producer;

import com.ewcode.friendsbigball.infra.kafka.KafkaBetProducer;
import org.junit.jupiter.api.Test;

class KafkaBetProducerTest {
    @Test
    void test() throws InterruptedException {
        KafkaBetProducer producer = new KafkaBetProducer();
        producer.test();
    }
}