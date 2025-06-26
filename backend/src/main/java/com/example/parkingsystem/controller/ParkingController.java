package com.example.parkingsystem.controller;

import com.example.parkingsystem.model.Vehicle;
import com.example.parkingsystem.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingController {

    @Autowired private ParkingService service;

    @PostMapping("/entry/park")
    public ResponseEntity<String> park(@RequestBody Vehicle vehicle) {
        String result = service.parkVehicle(vehicle);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/exit/leave")
    public ResponseEntity<String> leave(@RequestParam String plate) {
        int fee = service.checkout(plate);
        return ResponseEntity.ok("Total fee: Rp " + fee);
    }

    @GetMapping("/slots/status")
    public ResponseEntity<?> status() {
        return ResponseEntity.ok(service.getAllSlots());
    }
}
