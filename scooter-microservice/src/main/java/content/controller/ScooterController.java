package content.controller;
import content.service.ScooterService;
import content.entities.Scooter;
import content.entities.Stop;
import content.DTO.ScooterDTO;
import content.service.StopService;
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
    private final StopService stopService;


    @GetMapping
    public ResponseEntity<List<Scooter>> getAllScooters() {
        List<Scooter> scooterList = scooterService.findAllScooters();
        if (scooterList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(scooterList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scooter> getScooterById(@PathVariable("id") Long id) {
        Scooter scooter = scooterService.findScooterById(id);
        if (scooter == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(scooter);
    }

    @PostMapping
    public ResponseEntity<Scooter> createScooter(@RequestBody ScooterDTO scooterDTO) {
        Stop stop = stopService.findStopById(scooterDTO.getStopId());
        Scooter scooter = new Scooter(scooterDTO.getBattery(), scooterDTO.getLatitude(), scooterDTO.getLongitude(), scooterDTO.getKilometers(), scooterDTO.getTimeUsed(), stop, scooterDTO.getBaseFee(), scooterDTO.getExtraFeePause());
        Scooter newScooter = scooterService.saveScooter(scooter);
        if (newScooter == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newScooter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScooter(@PathVariable("id") Long id) {
        scooterService.deleteScooter(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Scooter> updateScooter(@PathVariable("id") Long id, @RequestBody Scooter scooter) {
        Scooter existentScooter = scooterService.findScooterById(id);

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
        existentScooter.setBaseFee(scooter.getBaseFee());

        Scooter updatedScooter = scooterService.updateScooter(existentScooter);

        return ResponseEntity.ok(updatedScooter);
    }

    @PutMapping("/{id}/is-available")
    public ResponseEntity<Scooter> updateAvailability(@PathVariable("id") Long id, @RequestParam("available") boolean available) {
        Scooter scooter = scooterService.findScooterById(id);

        if(scooter == null){
            return ResponseEntity.notFound().build();
        }

        scooter.setAvailability(available);
        scooterService.updateScooter(scooter);
        return ResponseEntity.ok(scooter);
    }

    @GetMapping("/trips/{minTrips}/{year}")
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

    @GetMapping("/report/kilometers/{km}")
    public ResponseEntity<List<Scooter>> getReportKilometers(@PathVariable("km") Double km){
        List<Scooter> scooters = scooterService.getScooterMoreKilometers(km);
        return ResponseEntity.ok(scooters);
    }
}