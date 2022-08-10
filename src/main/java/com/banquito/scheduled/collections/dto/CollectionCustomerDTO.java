package com.banquito.scheduled.collections.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionCustomerDTO {

    private String customerId;

    private String typeCustomerId;

    private String fullName;

}
