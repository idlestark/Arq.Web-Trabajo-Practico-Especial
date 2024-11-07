package service;
import client.ClientTrip;
import entities.Scooter;
import repository.ScooterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ScooterService {

    private final ScooterRepository scooterRepository;
    private final ClientTrip clientTrip;

    @Transactional(readOnly = true)
    public List<Scooter> findAll() {
        return scooterRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Scooter findById(Long id) {
        return ScooterRepository.findById(id).orElse(null);
    }

    @Transactional
    public Scooter save(Scooter scooter) {
        return ScooterRepository.save(scooter);
    }

    @Transactional
    public Scooter update(Scooter scooter) {
        return ScooterRepository.save(scooter);
    }

    @Transactional
    public void delete(Long id) {
        ScooterRepository.deleteById(id);
    }

    public Map<String, Long> getScootersStatus() {
        long operative = ScooterRepository.countByAvaileable();
        long underMaintenance = ScooterRepository.countByUnderMaintenance();
        return Map.of("Operative", operative, " Under maintenance", underMaintenance);
    }


    public List<Scooter> findClosestScooter(double latitude, double longitude, double radius) {
        return ScooterRepository.findClosestScooter(latitude, longitude, radius);
    }

    public List<Scooter> getScootersWithMostTrips(int minViajes, int anio) {
        return ClientTrip.getScootersWithMostTrips(minViajes, anio);
    }
}