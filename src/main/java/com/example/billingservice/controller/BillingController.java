package com.example.billingservice.controller;

import com.example.billingservice.enums.Status;
import com.example.billingservice.model.dto.BillingDto;
import com.example.billingservice.model.entity.Billing;
import com.example.billingservice.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/billing")
public class BillingController {
    @Autowired
    BillingService billingService;

    @PostMapping
    public ResponseEntity createBill(@RequestBody BillingDto billingDto) {
        return billingService.createBilling(billingDto);
    }
    @PutMapping("/{billingId}")
    public ResponseEntity updateBill(@PathVariable String billingId, @RequestBody BillingDto billingDto) {
        return billingService.updateBilling(billingId,billingDto);
    }
    @GetMapping
    public ResponseEntity getBillById(@RequestParam String id) {
        return billingService.getBillingById(id);
    }

    @GetMapping(path = "/billing-status")
    public ResponseEntity getBillingIdListByStatus(@RequestParam String status) {
        System.out.println(Status.valueOf(status));
        return billingService.getBillingIdsByStatus(Status.valueOf(status));
    }
}
