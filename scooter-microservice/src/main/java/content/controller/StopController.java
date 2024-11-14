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
@RequestMapping("/stop")
public class StopController {

    private final StopService stopService;

    @GetMapping
    public ResponseEntity<List<Stop>> getAllStops(){
        List<Stop> stops = stopService.findAllStops();
        if(stops.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(stops);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stop> getStopById(@PathVariable("id") Long id){
        Stop stop = stopService.findStopById(id);
        if(stop == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(stop);
    }

    @PostMapping
    public ResponseEntity<Stop> createStop(@RequestBody Stop stop){
        Stop stopCreated = stopService.saveStop(stop);
        if(stopCreated == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(stopCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Stop> deleteStop(@PathVariable("id") Long id){
        stopService.deleteStop(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stop> updateStop(@PathVariable("id") Long id, @RequestBody Stop stop){
        Stop existentStop = stopService.findStopById(id);
        if(existentStop == null){
            return ResponseEntity.noContent().build();
        }

        existentStop.setName(stop.getName());
        existentStop.setLatitude(stop.getLatitude());
        existentStop.setLongitude(stop.getLongitude());

        Stop updatedStop = stopService.saveStop(existentStop);

        return ResponseEntity.ok(updatedStop);
    }
}
