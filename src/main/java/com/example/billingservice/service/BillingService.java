package com.example.billingservice.service;

import com.example.billingservice.enums.Status;
import com.example.billingservice.model.dto.BillingDto;
import com.example.billingservice.model.entity.Billing;
import org.springframework.http.ResponseEntity;

public interface BillingService {
    ResponseEntity createBilling(BillingDto billing);
    ResponseEntity updateBilling(String billingId, BillingDto billing);
    ResponseEntity getBillingById(String id);
    ResponseEntity getBillingIdsByStatus(Status status);
}
