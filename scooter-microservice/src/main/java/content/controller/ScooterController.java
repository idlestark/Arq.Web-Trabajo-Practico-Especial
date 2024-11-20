package content.controller;
import content.service.ScooterService;
import content.entities.Scooter;
import content.entities.Stop;
import content.DTO.ScooterDTO;
import content.service.StopService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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


    @Operation(summary = "Get all scooters", description = "Gets a list of all registered scooters")
    @ApiResponse(responseCode = "200", description = "Scooters list obtained successfully")
    @GetMapping
    public ResponseEntity<List<Scooter>> getAllScooters() {
        List<Scooter> scooterList = scooterService.findAllScooters();
        if (scooterList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(scooterList);
    }

    @Operation(summary = "Get scooter by ID", description = "Gets a single scooter specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Scooter found successfully"),
            @ApiResponse(responseCode = "404", description = "Scooter not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Scooter> getScooterById(@PathVariable("id") Long id) {
        Scooter scooter = scooterService.findScooterById(id);
        if (scooter == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(scooter);
    }

    @Operation(summary = "Create scooter", description = "Creates a new scooter")
    @ApiResponse(responseCode = "201", description = "Scooter created successfully")
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

    @Operation(summary = "Delete scooter", description = "Deletes an existent scooter specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Scooter deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Scooter not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScooter(@PathVariable("id") Long id) {
        scooterService.deleteScooter(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update scooter", description = "Updates an existent scooter with the given information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Scooter updated successfully"),
            @ApiResponse(responseCode = "404", description = "Scooter not found")
    })
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

    @Operation(summary = "Update availability", description = "Updates availability status of a scooter specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Scooter updated successfully"),
            @ApiResponse(responseCode = "404", description = "Scooter not found")
    })
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

    @Operation(summary = "Update under maintenance status", description = "Updates 'under maintenance' status of a specified scooter by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Scooter updated successfully"),
            @ApiResponse(responseCode = "404", description = "Scooter not found")
    })
    @PutMapping("/{id}/is-under-maintenance-status")
    public ResponseEntity<Scooter> updateMaintenanceStatus(@PathVariable("id") Long id, @RequestParam("underMaintenance") boolean available){
        Scooter scooter = scooterService.findScooterById(id);

        scooter.setUnderMaintenance(available);
        scooterService.updateScooter(scooter);
        return ResponseEntity.ok(scooter);
    }

    @Operation(summary = "Get scooters with most trips", description = "Gets a list of the scooters with most trips")
    @ApiResponse(responseCode = "200", description = "Scooters list obtained successfully")
    @GetMapping("/trips/{minTrips}/{year}")
    public ResponseEntity<List<Scooter>> getScootersWithMostTrips (@RequestParam int minTrips, @RequestParam int year) {
        List<Scooter> result = scooterService.getScootersWithMostTrips(minTrips, year);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Get scooters status", description = "Gets a list of all operative and under maintenance scooters")
    @ApiResponse(responseCode = "200", description = "Scooters lists obtained successfully")
    @GetMapping("/status")
    public ResponseEntity<Map<String, Long>> getScootersStatus () {
        Map<String, Long> status = scooterService.getScooterStatus();
        return ResponseEntity.ok(status);
    }

    @Operation(summary = "Get nearby scooters", description = "Gets a list of all nearby scooters to a specified coordinates ")
    @ApiResponse(responseCode = "200", description = "Scooters list obtained successfully")
    @GetMapping("/nearby")
    public ResponseEntity<List<Scooter>> getNearbyScooters (@RequestParam double latitude, @RequestParam double longitude, @RequestParam double ratio) {
        List<Scooter> scooterList = scooterService.getNearbyScooters(latitude, longitude, ratio);
        return ResponseEntity.ok(scooterList);
    }

    @Operation(summary = "Get kilometers report", description = "Gets a list of scooters with less kilometers than the given amount")
    @ApiResponse(responseCode = "200", description = "Scooters list obtained successfully")
    @GetMapping("/kilometers-report/{km}")
    public ResponseEntity<List<Scooter>> getKilometersReport(@PathVariable("km") Double km){
        List<Scooter> scooters = scooterService.getKilometersReport(km);
        return ResponseEntity.ok(scooters);
    }
}