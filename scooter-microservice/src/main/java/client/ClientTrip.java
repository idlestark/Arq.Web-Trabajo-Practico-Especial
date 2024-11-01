package client;

import entities.Scooter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "viaje-service", url = "http://localhost:8003/trip")
public interface ClientTrip {

    @GetMapping("/monopatines-con-mas-viajes")
    List<Scooter> obtenerMonopatinesConMasViajes(
            @RequestParam("minViajes") int minViajes,
            @RequestParam("anio") int anio);

}