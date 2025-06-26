package com.example.parkingsystem.service;

import com.example.parkingsystem.model.*;
import com.example.parkingsystem.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {

    @Autowired private VehicleRepository vehicleRepo;
    @Autowired private SlotRepository slotRepo;
    @Autowired private FeeCalculatorService feeService;

    public String parkVehicle(Vehicle vehicle) {
        int floor = switch (vehicle.getType()) {
            case MOTORCYCLE -> 1;
            case CAR, BUS -> 2; // mulai dari lantai 2, cek hingga 3
        };

        int requiredSlots = switch (vehicle.getType()) {
            case MOTORCYCLE -> 1; // akan dibagi 4 motor per slot nanti
            case CAR -> 1;
            case BUS -> 4;
        };

        for (int f = floor; f <= 3; f++) {
            List<Slot> slots = slotRepo.findByFloor(f);
            List<Slot> available = slots.stream().filter(s -> !s.isOccupied()).toList();

            if (vehicle.getType() == VehicleType.BUS && available.size() >= 4) {
                vehicle.setEntryTime(LocalDateTime.now());
                vehicleRepo.save(vehicle);
                for (int i = 0; i < 4; i++) {
                    Slot s = available.get(i);
                    s.setOccupied(true);
                    s.setVehicleId(vehicle.getId());
                    slotRepo.save(s);
                }
                return "Bus parked on floor " + f;
            } else if (vehicle.getType() == VehicleType.CAR && available.size() >= 1) {
                vehicle.setEntryTime(LocalDateTime.now());
                vehicleRepo.save(vehicle);
                Slot s = available.get(0);
                s.setOccupied(true);
                s.setVehicleId(vehicle.getId());
                slotRepo.save(s);
                return "Car parked on floor " + f;
            } else if (vehicle.getType() == VehicleType.MOTORCYCLE) {
                // untuk 4 motor per slot, cari slot yang belum penuh
                for (Slot s : slots) {
                    long count = slotRepo.findAll().stream()
                        .filter(sl -> sl.getId().equals(s.getId()) && sl.isOccupied()).count();
                    if (count < 4) {
                        vehicle.setEntryTime(LocalDateTime.now());
                        vehicleRepo.save(vehicle);
                        s.setOccupied(true);
                        s.setVehicleId(vehicle.getId());
                        slotRepo.save(s);
                        return "Motorcycle parked on floor " + f;
                    }
                }
            }
        }
        return "No available slot for vehicle type: " + vehicle.getType();
    }

    public int checkout(String plate) {
        Optional<Vehicle> vehicleOpt = vehicleRepo.findByPlate(plate);
        if (vehicleOpt.isEmpty()) throw new RuntimeException("Vehicle not found!");

        Vehicle v = vehicleOpt.get();
        Duration durasi = Duration.between(v.getEntryTime(), LocalDateTime.now());

        int fee = feeService.calculate(v.getType(), durasi);

        List<Slot> usedSlots = slotRepo.findAll().stream()
            .filter(s -> s.getVehicleId() != null && s.getVehicleId().equals(v.getId()))
            .toList();

        for (Slot s : usedSlots) {
            s.setOccupied(false);
            s.setVehicleId(null);
            slotRepo.save(s);
        }

        vehicleRepo.delete(v);
        return fee;
    }

    public List<Slot> getAllSlots() {
        return slotRepo.findAll();
    }
}
