package com.banquito.scheduledcollections.service;

import com.banquito.scheduledcollections.dto.relational.TransactionDTO;
import com.banquito.scheduledcollections.enums.EventType;
import com.banquito.scheduledcollections.events.Event;
import com.banquito.scheduledcollections.events.PaymentsCreatedEvent;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CollectionEventsService {

  //private final KafkaTemplate<String, Event<?>> producer;

  private KafkaTemplate<String, Event<?>> producer;

  private String topicPayment;

  public void publish(TransactionDTO transacctions) {
    PaymentsCreatedEvent<TransactionDTO> created = new PaymentsCreatedEvent<>();
    created.setData(transacctions);
    created.setId(UUID.randomUUID().toString());
    created.setType(EventType.CREATED);
    created.setDate(new Date());

    this.producer.send(topicPayment, created);
  }
}
