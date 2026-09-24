package com.example.spring_project.service;

import com.example.spring_project.exception.CarNotFoundError;
import com.example.spring_project.model.Action;
import com.example.spring_project.model.RentalAuditLog;
import com.example.spring_project.model.RentalCar;
import com.example.spring_project.repository.CarRepository;
import com.example.spring_project.repository.RentalAuditLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    private final RentalAuditLogRepository rentalAuditLogRepository;
    private final CarRepository carRepository;

    public RentalService(RentalAuditLogRepository rentalAuditLogRepository,
                         CarRepository carRepository) {
        this.rentalAuditLogRepository = rentalAuditLogRepository;
        this.carRepository = carRepository;
    }

    public RentalCar carRent(Long carId, String customerName) {
        RentalCar car = carRepository.findById(carId).orElseThrow(() ->
                new CarNotFoundError("Car not found"));

        if (car.isRented()) {
            throw new IllegalArgumentException("Car with ID " + carId + " is already rented");
        }

        double finalDailyRate = car.getDailyRate();
        if ("Tesla".equalsIgnoreCase(car.getBrand())) {
            finalDailyRate *= 1.15;
        } else if ("Toyota".equalsIgnoreCase(car.getBrand())) {
            finalDailyRate *= 0.90;
        }

        car.setRented(true);
        car.setDailyRate(finalDailyRate);

        RentalCar updatedCar = carRepository.save(car);

        RentalAuditLog log =
                new RentalAuditLog(carId, customerName, Action.RENT);
        rentalAuditLogRepository.save(log);

        return updatedCar;
    }

    public RentalCar returnCar(Long carId, String customerName) {
        RentalCar car = carRepository.findById(carId).orElseThrow(() ->
                new CarNotFoundError("Car not found"));

        if (!car.isRented()) {
            throw new IllegalArgumentException("Car with ID " + carId + " is not currently rented");
        }

        car.setRented(false);
        RentalCar updatedCar = carRepository.save(car);

        RentalAuditLog log = new RentalAuditLog(carId, customerName, Action.RETURN);
        rentalAuditLogRepository.save(log);

        return updatedCar;
    }

    public List<RentalCar> getAllCars() {
        return carRepository.findAll();
    }

    public RentalCar getCarById(Long carId) {
        return carRepository.findById(carId).orElseThrow(() ->
                new CarNotFoundError("Car with ID " + carId + " not found"));
    }

    public RentalCar addCar(RentalCar car) {
        return carRepository.save(car);
    }
}
