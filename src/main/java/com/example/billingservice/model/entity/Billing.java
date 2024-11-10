package com.example.billingservice.model.entity;

import com.example.billingservice.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "billing")
public class Billing {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "billing_id")
    String billingId;

    @Column(name = "amount")
    double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;
}
