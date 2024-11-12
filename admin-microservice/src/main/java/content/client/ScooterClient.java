package content.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "scooter-service", url = "http://localhost:8002/scooter")
public interface ScooterClient {

    @PutMapping("/{id}/isAvaileable")
    void updateAvailability(@PathVariable("id") Long id, @RequestParam("availeable") boolean availeable);

    @PutMapping("/{id}/isUnderMaintenance")
    void updateMaintenanceStatus(@PathVariable("id") Long id, @RequestParam("underMaintenance") boolean availeable);
}
