package content.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@FeignClient(name = "scooter-microservice", url = "http://localhost:8002/scooter")
public interface ScooterClient {

    @PutMapping("/{id}/is-available")
    void updateAvailability(@PathVariable("id") Long id, @RequestParam("available") boolean available);

    @PutMapping("/{id}/is-under-maintenance")
    void updateMaintenanceStatus(@PathVariable("id") Long id, @RequestParam("underMaintenance") boolean available);

    @GetMapping("/status")
    Map<String, Long> getScooterStatus();
}
