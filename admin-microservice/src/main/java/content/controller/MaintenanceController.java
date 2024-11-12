package content.controller;
import content.dto.ReportKilometerDTO;
import content.entities.Maintenance;
import content.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @GetMapping
    public ResponseEntity<List<Maintenance>> getAllMaintenances() {
        List<Maintenance> maintenanceList = maintenanceService.findAll();
        if (maintenanceList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(maintenanceList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable("id") Long id) {
        Maintenance maintenance = maintenanceService.findById(id);
        if (maintenance == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(maintenance);
    }

    @PostMapping
    public ResponseEntity<Maintenance> createMaintenance(@RequestBody Maintenance maintenance) {
        Maintenance newMaintenance = maintenanceService.save(maintenance);
        if (newMaintenance == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newMaintenance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable("id") Long id) {
        maintenanceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> updateMaintenance(@PathVariable("id") Long id, @RequestBody Maintenance maintenance) {
        Maintenance existentMaintenance = maintenanceService.findById(id);

        if (existentMaintenance == null) {
            return ResponseEntity.notFound().build();
        }

        existentMaintenance.setDescription(maintenance.getDescription());
        existentMaintenance.setScooterId(maintenance.getScooterId());
        existentMaintenance.setStartDate(maintenance.getStartDate());
        existentMaintenance.setFinishDate(maintenance.getFinishDate());

        Maintenance updatedMaintenance = maintenanceService.update(existentMaintenance);

        return ResponseEntity.ok(updatedMaintenance);
    }

    @PostMapping("/start/{scooterId}")
    public ResponseEntity<Maintenance> startMaintenance (@PathVariable Long scooterId, @RequestBody(required = false) String description) {
        Maintenance newMaintenance = maintenanceService.startMaintenance(scooterId, description);
        return ResponseEntity.ok(newMaintenance);
    }

    @PutMapping("/end/{scooterId}")
    public ResponseEntity<Maintenance> endMaintenance(@PathVariable Long scooterId) {
        Maintenance maintenance = maintenanceService.endMaintenance(scooterId);
        return ResponseEntity.ok(maintenance);
    }

    @GetMapping("/report")
    public ResponseEntity<List<ReportKilometerDTO>> generateReport(@RequestParam boolean pauses) {
        return ResponseEntity.ok(maintenanceService.generateReport(pauses));
    }
}
