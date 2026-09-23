package content.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "trip-microservice", url = "http://localhost:8005/trip")
public interface ClientTrip {

    @GetMapping("/scooter-with-most-trips")
    List<Long> getScootersWithMostTrips(
            @RequestParam("minTrips") int minTrips,
            @RequestParam("year") int year);
}
