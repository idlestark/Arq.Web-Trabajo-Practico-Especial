package content.controller;
import content.service.ScooterService;
import content.entities.Scooter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/scooter")

public class ScooterController {

    private final ScooterService scooterService;


    @GetMapping
    public ResponseEntity<List<Scooter>> getAllScooters() {
        List<Scooter> scooterList = scooterService.findAll();
        if (scooterList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(scooterList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scooter> getScooterById(@PathVariable("id") Long id) {
        Scooter scooter = scooterService.findById(id);
        if (scooter == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(scooter);
    }

    @PostMapping
    public ResponseEntity<Scooter> createScooter(@RequestBody Scooter scooter) {
        Scooter scooterCreated = scooterService.save(scooter);
        if (scooterCreated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(scooterCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScooter(@PathVariable("id") Long id) {
        scooterService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Scooter> updateScooter(@PathVariable("id") Long id, @RequestBody Scooter scooter) {
        Scooter existentScooter = scooterService.findById(id);

        if (existentScooter == null) {
            return ResponseEntity.notFound().build();
        }

        existentScooter.setBattery(scooter.getBattery());
        existentScooter.setLatitude(scooter.getLatitude());
        existentScooter.setLongitude(scooter.getLongitude());
        existentScooter.setTimeUsed(scooter.getTimeUsed());
        existentScooter.setKilometers(scooter.getKilometers());
        existentScooter.setAvailability(scooter.isAvailable());
        existentScooter.setStop(scooter.getStop());

        Scooter scooterUpdated = scooterService.update(existentScooter);

        return ResponseEntity.ok(scooterUpdated);
    }


    @GetMapping("/trips")
    public ResponseEntity<List<Scooter>> getScootersWithMostTrips (@RequestParam int minTrips, @RequestParam int year) {
        List<Scooter> result = scooterService.getScootersWithMostTrips(minTrips, year);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Long>> getScooterStatus () {
        Map<String, Long> status = scooterService.getScooterStatus();
        return ResponseEntity.ok(status);
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<Scooter>> getNearbyScooters (@RequestParam double latitude, @RequestParam double longitude, @RequestParam double ratio) {
        List<Scooter> scooterList = scooterService.getNearbyScooters(latitude, longitude, ratio);
        return ResponseEntity.ok(scooterList);
    }
}