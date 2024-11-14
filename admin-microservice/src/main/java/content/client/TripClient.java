package content.client;
import content.DTO.TripDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "trip-service", url = "http://localhost:8005/trip")
public interface TripClient {
    @GetMapping("")
    List<TripDTO> getAllTrips();
}
