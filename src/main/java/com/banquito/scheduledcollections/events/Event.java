package com.banquito.scheduledcollections.events;

import com.banquito.scheduledcollections.enums.EventType;
import lombok.Data;

import java.util.Date;

@Data
public abstract class Event <T> {
    private String id;
    private Date date;
    private EventType type;
    private T data;
}