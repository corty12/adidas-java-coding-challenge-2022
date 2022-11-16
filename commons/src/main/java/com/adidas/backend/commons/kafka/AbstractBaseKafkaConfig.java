package com.adidas.backend.commons.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AbstractBaseKafkaConfig implements BaseKafkaConfig {

    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Override
    public Map<String, Object> baseKafkaConfigValues() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        //FIXME: do i need this?
//        configMap.put(
//                ConsumerConfig.GROUP_ID_CONFIG,
//                groupId);
//
        configMap.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        configMap.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);

        // TODO: ssl y avro
//        processMapAndAddIfNotEmpty(completeConfigMap, kafkaSSLConfigMap);
//        processMapAndAddIfNotEmpty(completeConfigMap, kafkaSchemaRegistryConfigMap);

        log.debug("Configuring Kafka Producer/Consumer.");

        return configMap;
    }

    @Override
    public Map<String, Object> baseKafkaProducerConfigValues() {
        return baseKafkaConfigValues();
    }

    @Override
    public Map<String, Object> baseKafkaConsumerConfigValues() {
        return baseKafkaConfigValues();
    }

}
