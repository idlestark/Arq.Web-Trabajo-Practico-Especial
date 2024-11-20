package content.service;
import content.client.ClientTrip;
import content.entities.Scooter;
import content.repository.ScooterRepository;
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
    public List<Scooter> findAllScooters() {
        return scooterRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Scooter findScooterById(Long id) {
        return scooterRepository.findById(id).orElse(null);
    }

    @Transactional
    public Scooter saveScooter(Scooter scooter) {
        return scooterRepository.save(scooter);
    }

    @Transactional
    public Scooter updateScooter(Scooter scooter) {
        return scooterRepository.save(scooter);
    }

    @Transactional
    public void deleteScooter(Long id) {
        scooterRepository.deleteById(id);
    }


    public Map<String, Long> getScooterStatus() {
        long operative = scooterRepository.countByOperativeAndAvailable();
        long underMaintenance = scooterRepository.countByUnderMaintenance();
        return Map.of("Operative and available", operative, "Under maintenance", underMaintenance);
    }


    public List<Scooter> getNearbyScooters(double latitude, double longitude, double radius) {
        return scooterRepository.findNearbyScooters(latitude, longitude, radius);
    }

    public List<Scooter> getScootersWithMostTrips(int minTrips, int year) {
        return clientTrip.getScootersWithMostTrips(minTrips, year);
    }

    public List<Scooter> getKilometersReport(Double km) {
        return scooterRepository.getKilometersReport(km);
    }
}
