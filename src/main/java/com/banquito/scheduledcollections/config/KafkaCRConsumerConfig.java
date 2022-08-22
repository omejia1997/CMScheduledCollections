package com.banquito.scheduledcollections.config;

import com.banquito.core.account.dto.TransactionDTO;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaCRConsumerConfig {
  @Bean
  public ConsumerFactory<String, List<TransactionDTO>> consumerFactory() {
    Map<String, Object> config = new HashMap<>();
    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "34.125.80.201:9092");
    config.put(ConsumerConfig.GROUP_ID_CONFIG, "foo2");
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
    ObjectMapper om = new ObjectMapper();
    JavaType type = om.getTypeFactory().constructParametricType(List.class, TransactionDTO.class);
    return new DefaultKafkaConsumerFactory<>(
        config,
        new StringDeserializer(),
        new JsonDeserializer<List<TransactionDTO>>(type, om, false));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, List<TransactionDTO>> fooListener() {
    ConcurrentKafkaListenerContainerFactory<String, List<TransactionDTO>> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}
