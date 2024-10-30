package com.example.microserviciobike.controller;
import com.example.microserviciobike.entity.Bike;
import com.example.microserviciobike.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/scooter")
public class skateboardController {

    @Autowired
    ScooterService scooterService;

    @GetMapping("/")
    public ResponseEntity<List<Scooter>> getAllScooter() {
        List<Scooter> scooters = scooterService.getAll();
        if (scooters.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(scooters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scooter> getScooterById(@PathVariable("id") Long id) {
        Scooter scooter = scooterService.findById(id);
        if (scooter == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(scooter);
    }

    @PostMapping("")
    public ResponseEntity<Bike> save(@RequestBody Bike bike) {
        Bike bikeNew = bikeService.save(bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<Bike>> getCarsByUserId(@PathVariable("userId") Long userId) {
        List<Bike> bikes = bikeService.byUserId(userId);
        return ResponseEntity.ok(bikes);
    }
}
