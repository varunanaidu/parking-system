package com.example.parkingsystem.service;

import com.example.parkingsystem.model.VehicleType;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class FeeCalculatorService {

    public int calculate(VehicleType type, Duration duration) {
        long hours = Math.max(1, duration.toHours())

        return switch (type) {
            case MOTORCYCLE -> (int) (hours * 2000);
            case CAR -> (int) (hours * 4000);
            case BUS -> (int) (hours * 6000);
        };
    }
}
