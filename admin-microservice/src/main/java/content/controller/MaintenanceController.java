package content.controller;

import content.DTO.ReportKilometerDTO;
import content.entities.Maintenance;
import content.service.MaintenanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @Operation(summary = "Get all maintenances", description = "Gets a list of all maintenances")
    @ApiResponse(responseCode = "200", description = "Maintenance list obtained successfully")
    @GetMapping
    public ResponseEntity<List<Maintenance>> getAllMaintenances() {
        List<Maintenance> maintenanceList = maintenanceService.findAllMaintenance();
        if (maintenanceList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(maintenanceList);
    }

    @Operation(summary = "Get maintenance by ID", description = "Gets a single maintenance specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Maintenance found successfully"),
            @ApiResponse(responseCode = "404", description = "Maintenance not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable("id") Long id) {
        Maintenance maintenance = maintenanceService.findMaintenanceById(id);
        if (maintenance == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(maintenance);
    }

    @Operation(summary = "Create maintenance", description = "Create a new maintenance")
    @ApiResponse(responseCode = "201", description = "Maintenance created successfully")
    @PostMapping
    public ResponseEntity<Maintenance> createMaintenance(@RequestBody Maintenance maintenance) {
        Maintenance newMaintenance = maintenanceService.saveMaintenance(maintenance);
        if (newMaintenance == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(201).body(newMaintenance);
    }

    @Operation(summary = "Delete maintenance", description = "Delete an existent maintenance specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Maintenance deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Maintenance not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable("id") Long id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update maintenance", description = "Updates an existent maintenance with the given information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Maintenance updated successfully"),
            @ApiResponse(responseCode = "404", description = "Maintenance not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> updateMaintenance(@PathVariable("id") Long id, @RequestBody Maintenance maintenance) {
        Maintenance existentMaintenance = maintenanceService.findMaintenanceById(id);

        if (existentMaintenance == null) {
            return ResponseEntity.notFound().build();
        }

        existentMaintenance.setDescription(maintenance.getDescription());
        existentMaintenance.setScooterId(maintenance.getScooterId());
        existentMaintenance.setStartDate(maintenance.getStartDate());
        existentMaintenance.setFinishDate(maintenance.getFinishDate());

        Maintenance updatedMaintenance = maintenanceService.updateMaintenance(existentMaintenance);
        return ResponseEntity.ok(updatedMaintenance);
    }

    @Operation(summary = "Start maintenance", description = "Starts a new maintenance for a scooter")
    @ApiResponse(responseCode = "201", description = "Maintenance started successfully")
    @PostMapping("/start/{scooterId}")
    public ResponseEntity<Maintenance> startMaintenance(@PathVariable("scooterId") Long scooterId, @RequestBody(required = false) String description) {
        Maintenance newMaintenance = maintenanceService.startMaintenance(scooterId, description != null ? description : "Scheduled maintenance");
        return ResponseEntity.status(201).body(newMaintenance);
    }

    @Operation(summary = "End maintenance", description = "Ends an existing maintenance for a scooter")
    @ApiResponse(responseCode = "200", description = "Maintenance ended successfully")
    @PutMapping("/end/{scooterId}")
    public ResponseEntity<Maintenance> endMaintenance(@PathVariable("scooterId") Long scooterId) {
        Maintenance maintenance = maintenanceService.endMaintenance(scooterId);
        return ResponseEntity.ok(maintenance);
    }

    @Operation(summary = "Generate report", description = "Gets a maintenance/kilometers report")
    @ApiResponse(responseCode = "200", description = "Report generated successfully")
    @GetMapping("/report")
    public ResponseEntity<List<ReportKilometerDTO>> generateReport(@RequestParam(defaultValue = "true") boolean pauses) {
        return ResponseEntity.ok(maintenanceService.generateReport(pauses));
    }
}
