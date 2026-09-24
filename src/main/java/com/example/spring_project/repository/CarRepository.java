package com.example.spring_project.repository;

import com.example.spring_project.model.RentalCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<RentalCar, Long> {

}
