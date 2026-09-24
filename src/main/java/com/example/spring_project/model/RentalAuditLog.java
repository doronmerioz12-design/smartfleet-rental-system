package com.example.spring_project.model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "rental_audit_log")
public class RentalAuditLog {

    @Id
    private String id;

    private Long carId;
    private String customerName;
    private Action action;
    private LocalDateTime timestamp;

    public RentalAuditLog() {}

    public RentalAuditLog(Long id, String customerName, Action action) {
        this.carId = id;
        this.customerName = customerName;
        this.action = action;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }
    public void setCarId(Long carId) {
        this.carId = carId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public Action getAction() {
        return action;
    }
    public void setAction(Action action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
