package com.example.microservicioScooter.controller;
import com.example.microservicioScooter.entity.Scooter;
import com.example.microservicioScooter.service.ScooterService;
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
        List<Scooter> scooterList = scooterService.getAll();
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
    public ResponseEntity<Scooter> save(@RequestBody Scooter scooter) {
        Scooter newScooter = scooterService.save(scooter);
        return ResponseEntity.ok(newScooter);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Scooter>> getCarsByUserId(@PathVariable("userId") Long userId) {
        List<Scooter> scooterList = scooterService.byUserId(userId);
        return ResponseEntity.ok(scooterList);
    }
}
