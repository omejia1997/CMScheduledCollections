package com.banquito.scheduledcollections.events;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentsCreatedEvent<T> extends Event<T> {}
