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
    public List<Stop> findAll() {
        return stopRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Stop findById(Long id) {
        return stopRepository.findById(id).orElse(null);
    }

    @Transactional
    public Stop save(Stop stop) {
        return stopRepository.save(stop);
    }

    @Transactional
    public void delete(Long id) {
        stopRepository.deleteById(id);
    }

    @Transactional
    public Stop update(Stop stop) {
        return stopRepository.save(stop);
    }

}
