package com.example.billingservice.repository;

import com.example.billingservice.enums.Status;
import com.example.billingservice.model.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BillingRepository extends JpaRepository<Billing, String> {
    @Query("SELECT b.billingId FROM Billing b WHERE b.status = :status")
    List<String> findBillingIdByStatus(@Param("status") Status status);
}
