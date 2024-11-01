package com.example.microservicioScooter.controller;
import com.example.microserviciobike.entity.Bike;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @GetMapping("/")
    public ResponseEntity<List<Maintenance>> getAllMaintenance() {
        List<Maintenance> list = maintenanceService.getAll();
        if (list.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceByid(@PathVariable("id") Long id) {
        Maintenance maintenance = MaintenanceService.findById(id);
        if (maintenance == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(maintenance);
    }

    @PostMapping
    public ResponseEntity<Maintenance> createMaintenance(@RequestBody Maintenance maintenance){
        Maintenace salida = maintenance.save(maintenance);
        if(maintenance == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(maintenance);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<void> deleteMaintenance(@PathVariable("id") Long id){
        Maintenance mantenanceExist = maintenanceService.findById(id);
    }
}
