package content.controller;

import content.service.StopService;
import content.entities.Stop;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/parada")
public class StopController {

    private final StopService stopService;

    @GetMapping
    public ResponseEntity<List<Stop>> getAllParadas(){
        List<Stop> stops = stopService.findAll();
        if(stops.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(stops);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stop> getParadaById(@PathVariable("id") Long id){
        Stop stop = stopService.findById(id);
        if(stop == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(stop);
    }

    @PostMapping
    public ResponseEntity<Stop> createParada(@RequestBody Stop stop){
        Stop stopCreated = stopService.save(stop);
        if(stopCreated == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(stopCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Stop> deleteParada(@PathVariable("id") Long id){
        stopService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stop> updateParada(@PathVariable("id") Long id, @RequestBody Stop stop){
        Stop stopExistente = stopService.findById(id);
        if(stopExistente == null){
            return ResponseEntity.noContent().build();
        }

        stopExistente.setName(stop.getName());
        stopExistente.setLatitude(stop.getLatitude());
        stopExistente.setLongitude(stop.getLongitude());

        Stop stopUpdated = stopService.save(stopExistente);

        return ResponseEntity.ok(stopUpdated);
    }
}
