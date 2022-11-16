package com.adidas.backend.commons.kafka;

import org.springframework.kafka.annotation.EnableKafka;

import java.util.Map;

@EnableKafka
public interface BaseKafkaConfig {
    Map<String, Object> baseKafkaConfigValues();

    Map<String, Object> baseKafkaProducerConfigValues();

    Map<String, Object> baseKafkaConsumerConfigValues();
}
