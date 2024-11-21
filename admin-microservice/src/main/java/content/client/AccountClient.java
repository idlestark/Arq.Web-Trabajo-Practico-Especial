package content.client;
import content.DTO.ScooterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "user-microservice", url = "http://localhost:8006/account")
public interface AccountClient {

    @PutMapping("/cancel/{id}")
    void cancelAccount(@PathVariable("id") long id);

    @GetMapping("/trips/{minTrip}/{year}")
    List<ScooterDTO> getScootersWithMostTrips(@PathVariable("minTrip") int minTrip, @PathVariable("year") int year);

}