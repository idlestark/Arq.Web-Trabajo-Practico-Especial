package content.service;
import content.entities.Stop;
import content.repository.StopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StopService {

    private final StopRepository stopRepository;

    @Transactional(readOnly = true)
    public List<Stop> findAllStops() {
        return stopRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Stop findStopById(Long id) {
        return stopRepository.findById(id).orElse(null);
    }

    @Transactional
    public Stop saveStop(Stop stop) {
        return stopRepository.save(stop);
    }

    @Transactional
    public void deleteStop(Long id) {
        stopRepository.deleteById(id);
    }

    @Transactional
    public Stop updateStop(Stop stop) {
        return stopRepository.save(stop);
    }

}
