package client;
import entities.Scooter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name = "trip-service", url = "http://localhost:8003/trip")
public interface ClientTrip {

    @GetMapping("/scooters-with-most-trips")
    List<Scooter> getScootersWithMostTrips(
            @RequestParam("minTrips") int minTrips,
            @RequestParam("year") int year);

}