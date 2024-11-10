package com.example.billingservice.model.dto;

import com.example.billingservice.enums.Status;
import lombok.Data;

@Data
public class BillingDto {
    String billingId;
    double amount;
    Status status;
}
