package com.adidas.backend.publicservice.producer;

import com.adidas.backend.publicservice.model.QueueSubscriptionBean;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SubscriptionsProducerConfig /*extends AbstractBaseKafkaConfig*/ {


    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.schemaRegistryAddress}")
    private String schemaRegistryAddress;

    @Bean
    public ProducerFactory<String, QueueSubscriptionBean> producerFactory() {
//        Map<String, Object> configProps = baseKafkaProducerConfigValues();
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        configProps.put("schema.registry.url", schemaRegistryAddress);
        return new DefaultKafkaProducerFactory<>(configProps);

    }

    @Bean
    public KafkaTemplate<String, QueueSubscriptionBean> kafkaTemplate() {
        return new KafkaTemplate<String, QueueSubscriptionBean>(producerFactory());
    }
}
