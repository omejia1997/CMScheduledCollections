package com.banquito.scheduledcollections.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaCRTopicConfig {
  @Bean
  public NewTopic banquitoCollRTopic() {
    return TopicBuilder.name("collections_recurrement").build();
  }
}
