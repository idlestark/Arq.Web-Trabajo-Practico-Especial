package content.controller;
import content.DTO.KilometersReportDTO;
import content.entities.Trip;
import content.entities.Pause;
import content.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trip")
public class TripController {


    private final TripService tripService;


    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips(){
        List<Trip> trips = tripService.findAllTrips();
        if(trips.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTrip(@PathVariable("id") Long id){
        Trip trip = tripService.findTripById(id);
        if(trip == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trip);
    }

    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody Trip Trip) {
        Trip tripCreated = tripService.saveTrip(Trip);
        if(tripCreated == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tripCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Trip> deleteTrip(@PathVariable("id") Long id){
        tripService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable("id") Long id, @RequestBody Trip trip){
        Trip existingTrip = tripService.findTripById(id);;
        if(existingTrip == null){
            return ResponseEntity.noContent().build();
        }

        existingTrip.setStartDate(trip.getStartDate());
        existingTrip.setEndDate(trip.getEndDate());
        existingTrip.setScooterId(trip.getScooterId());
        existingTrip.setInProgress(trip.isInProgress());
        existingTrip.setKilometers(trip.getKilometers());

        Trip updatedTrip = tripService.saveTrip(existingTrip);
        return ResponseEntity.ok(updatedTrip);
    }

    @GetMapping("/{tripId}/total-time-with-pauses")
    public ResponseEntity<Double> ge(@PathVariable("tripId") Long tripId) {
        Double totalTimeWithPauses = tripService.getTotalTimeWithPauses(tripId);
        return ResponseEntity.ok(totalTimeWithPauses);
    }

    @PutMapping("/{tripId}/finish")
    public ResponseEntity<Trip> endTrip(@PathVariable Long tripId, @RequestParam double kilometers) {
        try {
            Trip finalizedTrip = tripService.endTrip(tripId, kilometers);
            return ResponseEntity.ok(finalizedTrip);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/addPause/{id}")
    public ResponseEntity<Trip> addPause(@PathVariable("id") Long id, @RequestBody Pause pause) {
        Trip existingTrip = tripService.findTripById(id);
        if (existingTrip == null) {
            return ResponseEntity.noContent().build();
        }

        pause.setTrip(existingTrip);
        existingTrip.getPauses().add(pause);
        tripService.saveTrip(existingTrip);
        return ResponseEntity.ok(existingTrip);
    }

    @GetMapping("/reports/kilometers")
    public ResponseEntity<List<KilometersReportDTO>> generateKilometersReport() {
        List<KilometersReportDTO> report = tripService.generateKilometersReport();
        if (report.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(report);
    }

}