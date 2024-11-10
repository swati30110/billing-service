package com.example.billingservice.service;

import com.example.billingservice.enums.Status;
import com.example.billingservice.exception.RecordNotFound;
import com.example.billingservice.model.dto.BillingDto;
import com.example.billingservice.model.entity.Billing;
import com.example.billingservice.repository.BillingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository billingRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(BillingServiceImpl.class);

    public ResponseEntity createBilling(BillingDto billing) {
        LOGGER.info("Bill is Generating {}", billing.toString());
        Billing savedBilling = billingRepository.save(objectMapper.convertValue(billing, Billing.class));
        URI location = URI.create("/api/billing/" + savedBilling.getBillingId());
        LOGGER.info("Bill is Created {}", savedBilling.toString());
        return ResponseEntity.created(location).body(savedBilling);
    }

    @Override
    public ResponseEntity updateBilling(String billingId, BillingDto billing) {
        LOGGER.info("Bill is updating {} for billingId {}", billing.toString(), billingId);
        Billing savedBilling = billingRepository.findById(billingId).orElseThrow(
                () -> new RecordNotFound("Not a valid Billing Id")
        );
        savedBilling.setStatus(billing.getStatus());
        Billing updatedBilling = billingRepository.save(savedBilling);
        LOGGER.info("Billing record updated with ID: {}", billingId);
        return ResponseEntity.ok(updatedBilling);
    }

    @Override
    public ResponseEntity getBillingById(String id) {
        Optional<Billing> savedBilling = billingRepository.findById(id);
        if(savedBilling.isPresent()) {
            return ResponseEntity.ok(objectMapper.convertValue(savedBilling.get(), Billing.class));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity getBillingIdsByStatus(Status status) {
        List<String> BillingIdList = billingRepository.findBillingIdByStatus(Status.valueOf(status.name()));
        if(BillingIdList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(BillingIdList);
    }
}
