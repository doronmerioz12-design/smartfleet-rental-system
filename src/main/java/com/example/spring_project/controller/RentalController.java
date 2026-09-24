package com.example.spring_project.controller;

import com.example.spring_project.model.RentalCar;
import com.example.spring_project.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/cars")
    public List<RentalCar> getAllCars() {
        return rentalService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public RentalCar getCarById(@PathVariable Long id) {
        return rentalService.getCarById(id);
    }

    @PostMapping("/cars/{id}/rent")
    public RentalCar rentCar(@PathVariable Long id, @RequestParam String customerName) {
        return rentalService.carRent(id, customerName);
    }

    @PostMapping("/cars/{id}/return")
    public RentalCar returnCar(@PathVariable Long id, @RequestParam String customerName) {
        return rentalService.returnCar(id, customerName);
    }

    @PostMapping("/cars")
    public RentalCar addCar(@RequestBody RentalCar car) {
        return rentalService.addCar(car);
    }
}
