package com.example.spring_project.repository;

import com.example.spring_project.model.RentalAuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RentalAuditLogRepository extends MongoRepository<RentalAuditLog, String> {
}
