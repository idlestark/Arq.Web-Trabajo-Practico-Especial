package content.service;
import content.entities.Trip;
import org.springframework.stereotype.Service;
import content.entities.Pause;
import content.repository.PauseRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PauseService {

    private final PauseRepository pauseRepository;


    public List<Pause> findAllPauses() {
        return pauseRepository.findAll();
    }

    public Pause findPauseById(Long id) {
        return pauseRepository.findById(id).orElse(null);
    }

    public Pause savePause(Pause pause) {
        return pauseRepository.save(pause);
    }

    public Pause updatePause(Long tripId, Pause pauseDetails) {
        Pause existentPause = pauseRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Pause not found"));
        if (existentPause != null) {
            existentPause.setStartDate(pauseDetails.getStartDate());
            existentPause.setEndDate(pauseDetails.getEndDate());
            existentPause.setDuration(pauseDetails.getDuration());
            return pauseRepository.save(existentPause);
        }
        return null;
    }

    public void deletePause(Long id) {
        pauseRepository.deleteById(id);
    }
}
