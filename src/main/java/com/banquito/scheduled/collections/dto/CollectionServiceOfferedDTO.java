package com.banquito.scheduled.collections.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionServiceOfferedDTO {
    private String name;

    private String description;
}
