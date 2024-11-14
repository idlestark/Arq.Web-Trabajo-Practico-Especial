package content.client;
import content.DTO.ScooterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "user-scooter-microservice", url = "http://localhost:8002/user-scooter")
public interface UserScooterClient {

    @GetMapping("/nearby")
    List<ScooterDTO> getNearbyScooters(@RequestParam("latitude") double latitude
            , @RequestParam("longitude") double longitude
            , @RequestParam("radius") double radius);
}