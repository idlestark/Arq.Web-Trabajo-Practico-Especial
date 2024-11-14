package content.controller;
import content.entities.Pause;
import content.service.PauseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pause")
public class PauseController {

    private final PauseService pauseService;

    @GetMapping
    public ResponseEntity<List<Pause>> getAllPauses() {
        List<Pause> pauses = pauseService.findAllPauses();
        if (pauses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pauses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pause> getPauseById(@PathVariable Long id) {
        Pause pause = pauseService.findPauseById(id);
        return pause != null ? ResponseEntity.ok(pause) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pause> createPause(@RequestBody Pause pause) {
        Pause createdPause = pauseService.savePause(pause);
        return ResponseEntity.ok(createdPause);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pause> updatePause(@PathVariable Long id, @RequestBody Pause pause) {
        Pause updatedPause = pauseService.updatePause(id, pause);
        return updatedPause != null ? ResponseEntity.ok(updatedPause) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePause(@PathVariable Long id) {
        pauseService.deletePause(id);
        return ResponseEntity.noContent().build();
    }
}