package content.controller;
import content.service.StopService;
import content.entities.Stop;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all stops", description = "Gets a list of all stops")
    @ApiResponse(responseCode = "200", description = "Stops list obtained successfully")
    @GetMapping
    public ResponseEntity<List<Stop>> getAllStops(){
        List<Stop> stops = stopService.findAllStops();
        if(stops.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(stops);
    }

    @Operation(summary = "Get stop by ID", description = "Gets a single stop specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stop found successfully"),
            @ApiResponse(responseCode = "404", description = "Stop not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Stop> getStopById(@PathVariable("id") Long id){
        Stop stop = stopService.findStopById(id);
        if(stop == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(stop);
    }


    @Operation(summary = "Create stop", description = "Creates a new stop")
    @ApiResponse(responseCode = "201", description = "Stop created successfully")
    @PostMapping
    public ResponseEntity<Stop> createStop(@RequestBody Stop stop){
        Stop stopCreated = stopService.saveStop(stop);
        if(stopCreated == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(stopCreated);
    }

    @Operation(summary = "Delete stop", description = "Deletes an existent stop")
    @ApiResponse(responseCode = "200", description = "Stop deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Stop> deleteStop(@PathVariable("id") Long id){
        stopService.deleteStop(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update stop", description = "Updates an existent stop with the given information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Stop updated successfully"),
            @ApiResponse(responseCode = "404", description = "Stop not found")
    })
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
