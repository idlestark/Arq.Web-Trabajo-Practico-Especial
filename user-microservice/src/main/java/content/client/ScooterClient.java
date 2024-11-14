package content.client;
import content.DTO.ScooterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "scooter-microservice", url = "http://localhost:8002/scooter")
public interface ScooterClient {

    @GetMapping("/nearby")
    List<ScooterDTO> getNearbyScooters(@RequestParam("latitude") double latitude
            , @RequestParam("longitude") double longitude
            , @RequestParam("radius") double radius);
}