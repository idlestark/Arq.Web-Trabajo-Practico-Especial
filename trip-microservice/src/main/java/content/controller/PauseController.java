package content.controller;
import content.entities.Pause;
import content.service.PauseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pause")
public class PauseController {

    private final PauseService pauseService;

    @Operation(summary = "Get all pauses", description = "Gets a list of all pauses")
    @ApiResponse(responseCode = "200", description = "Pauses list obtained successfully")
    @GetMapping
    public ResponseEntity<List<Pause>> getAllPauses() {
        List<Pause> pauses = pauseService.findAllPauses();
        if (pauses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pauses);
    }

    @Operation(summary = "Get pause by ID", description = "Gets a single pause specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pause found successfully"),
            @ApiResponse(responseCode = "404", description = "Pause not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pause> getPauseById(@PathVariable Long id) {
        Pause pause = pauseService.findPauseById(id);
        return pause != null ? ResponseEntity.ok(pause) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create pause", description = "Creates a new pause")
    @ApiResponse(responseCode = "201", description = "Pause created successfully")
    @PostMapping
    public ResponseEntity<Pause> createPause(@RequestBody Pause pause) {
        Pause createdPause = pauseService.savePause(pause);
        return ResponseEntity.ok(createdPause);
    }

    @Operation(summary = "Update pause", description = "Updates an existent pause with the given information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pause updated successfully"),
            @ApiResponse(responseCode = "404", description = "Pause not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Pause> updatePause(@PathVariable Long id, @RequestBody Pause pause) {
        Pause updatedPause = pauseService.updatePause(id, pause);
        return updatedPause != null ? ResponseEntity.ok(updatedPause) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete pause", description = "Deletes an existent pause")
    @ApiResponse(responseCode = "200", description = "Pause deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePause(@PathVariable Long id) {
        pauseService.deletePause(id);
        return ResponseEntity.noContent().build();
    }
}