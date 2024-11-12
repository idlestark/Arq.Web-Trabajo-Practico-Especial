package content.controller;
import content.entities.Trip;
import content.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trip")
public class TripController {


    private final TripService tripService;


    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrip() {
        List<Trip> tripList = tripService.findAll();
        if (tripList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tripList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable("id") Long id) {
        Trip trip = tripService.findById(id);
        if (trip == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(trip);
    }

    @PostMapping
    public ResponseEntity<Trip> createScooter(@RequestBody Trip trip) {
        Trip newTrip = tripService.save(trip);
        if (newTrip == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newTrip);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable("id") Long id) {
        tripService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateViaje(@PathVariable("id") Long id, @RequestBody Trip trip){
        Trip existientTrip = tripService.findById(id);
        if(existientTrip == null){
            return ResponseEntity.noContent().build();
        }

        existientTrip.setStartDate(trip.getStartDate());
        existientTrip.setEndDate(trip.getEndDate());
        existientTrip.setScooterId(trip.getScooterId());
        existientTrip.setInProgress(trip.isinProgress());
        existientTrip.setKilometers(trip.getKilometers());

        Trip Updatedtrip = tripService.save(existientTrip);
        return ResponseEntity.ok(Updatedtrip);
    }
    //hola
    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> getScootersWithMostTrips(@RequestParam int minTrips, @RequestParam int year) {
        List<Trip> result = tripService.getScootersWithMostTrips(tripService, year);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{tripId}/total-times-with-stops")
    public ResponseEntity<Double> getTotalTimeWithPauses(@PathVariable("viajeId") Long viajeId) {
        Double totalTimeWithPauses = tripService.getTotalTimeWithPauses(viajeId);
        return ResponseEntity.ok(totalTimeWithPauses);
    }


    @GetMapping("/status")
    public ResponseEntity<Map<String, Long>> getStateScooter() {
        Map<String, Long> estado = tripService.getStateScooter();
        return ResponseEntity.ok(estado);
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<Trip>> obtenerMonopatinesCercanos(
            @RequestParam double latitud, @RequestParam double longitud, @RequestParam double radio) {
        List<Trip> monopatines = tripService.obtenerMonopatinesCercanos(latitud, longitud, radio);
        return ResponseEntity.ok(monopatines);
    }
}