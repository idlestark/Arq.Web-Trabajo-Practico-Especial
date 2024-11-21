package content.controller;
import content.DTO.KilometersReportDTO;
import content.entities.Trip;
import content.entities.Pause;
import content.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trip")
public class TripController {


    private final TripService tripService;

    @Operation(summary = "Get all trips", description = "Gets a list of all trips")
    @ApiResponse(responseCode = "200", description = "Trip list obtained successfully")
    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips(){
        List<Trip> trips = tripService.findAllTrips();
        if(trips.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trips);
    }

    @Operation(summary = "Get trip by ID", description = "Gets a single trip specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trip found successfully"),
            @ApiResponse(responseCode = "404", description = "Trip not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTrip(@PathVariable("id") Long id){
        Trip trip = tripService.findTripById(id);
        if(trip == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trip);
    }
    @Operation(summary = "Create trip", description = "Creates a new trip")
    @ApiResponse(responseCode = "201", description = "Trip created successfully")
    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody Trip Trip) {
        Trip tripCreated = tripService.saveTrip(Trip);
        if(tripCreated == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tripCreated);
    }

    @Operation(summary = "Delete trip", description = "Deletes an existent trip")
    @ApiResponse(responseCode = "200", description = "Trip deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Trip> deleteTrip(@PathVariable("id") Long id){
        tripService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update trip", description = "Updates an existent trip with the given information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Trip updated successfully"),
            @ApiResponse(responseCode = "404", description = "Trip not found")
    })
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

    @Operation(summary = "Get total time with pauses", description = "Gets the total time with pauses")
    @ApiResponse(responseCode = "200", description = "Total time with pauses obtained successfully")
    @GetMapping("/{tripId}/total-time-with-pauses")
    public ResponseEntity<Double> getTotalTimeWithPauses(@PathVariable("tripId") Long tripId) {
        Double totalTimeWithPauses = tripService.getTotalTimeWithPauses(tripId);
        return ResponseEntity.ok(totalTimeWithPauses);
    }


    @Operation(summary = "End trip", description = "Ends a trip specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Trip ended successfully"),
            @ApiResponse(responseCode = "404", description = "Trip not found")
    })
    @PutMapping("/{tripId}/finish")
    public ResponseEntity<Trip> endTrip(@PathVariable Long tripId, @RequestParam double kilometers) {
        try {
            Trip finalizedTrip = tripService.endTrip(tripId, kilometers);
            return ResponseEntity.ok(finalizedTrip);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Operation(summary = "Add pause", description = "Adds a pause to a trip, specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pause added successfully"),
            @ApiResponse(responseCode = "404", description = "Pause not found")
    })
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

    @Operation(summary = "Get kilometers report", description = "Gets the kilometer report")
    @ApiResponse(responseCode = "200", description = "Kilometers report obtained successfully")
    @GetMapping("/kilometers-report")
    public ResponseEntity<List<KilometersReportDTO>> getKilometersReport() {
        List<KilometersReportDTO> report = tripService.getKilometersReport();
        if (report.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(report);
    }

}