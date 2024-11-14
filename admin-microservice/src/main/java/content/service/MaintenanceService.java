package content.service;
import content.client.ScooterClient;
import content.client.TripClient;
import content.DTO.ReportKilometerDTO;
import content.DTO.TripDTO;
import content.entities.Maintenance;
import content.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final ScooterClient scooterClient;
    private final TripClient tripClient;

    @Transactional(readOnly = true)
    public List<Maintenance> findAllMaintenance() {
        return maintenanceRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Maintenance findMaintenanceById(Long id) {
        return maintenanceRepository.findById(id).orElse(null);
    }

    @Transactional
    public Maintenance saveMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    @Transactional
    public void deleteMaintenance(Long id) {
        maintenanceRepository.deleteById(id);
    }

    @Transactional
    public Maintenance updateMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    @Transactional
    public Maintenance startMaintenance(Long scooterId, String description) {

        if (maintenanceRepository.findByScooter(scooterId)) {
            throw new RuntimeException("Requested scooter is under maintenance");
        }

        Maintenance maintenance = new Maintenance();
        maintenance.setScooterId(scooterId);
        maintenance.setStartDate(LocalDateTime.now());
        maintenance.setDescription(description);
        maintenanceRepository.save(maintenance);

        scooterClient.updateAvailability(scooterId, false);
        scooterClient.updateMaintenanceStatus(scooterId, true);

        return maintenance;
    }

    @Transactional
    public Maintenance endMaintenance(Long scooterId) {

        Maintenance maintenance = maintenanceRepository.findById(scooterId).orElse(null);

        maintenance.setFinishDate(LocalDateTime.now());
        maintenanceRepository.save(maintenance);

        scooterClient.updateAvailability(scooterId, true);
        scooterClient.updateMaintenanceStatus(scooterId, false);

        return maintenance;
    }

    public List<ReportKilometerDTO> generateReport (boolean pauses) {
        List<TripDTO> tripList = tripClient.getAllTrips();
        return tripList.stream()
                .map(t -> {
                    double totalTime = t.getUseTime();

                    if (!pauses && t.getPauses() != null) {
                        double pauseTime = t.getPauses().stream()
                                .filter(p -> p.getEndDate() != null)
                                .mapToDouble(p -> Duration.between(p.getStartDate(), p.getEndDate()).toMinutes())
                                .sum();
                        totalTime -= pauseTime;
                    }

                    return new ReportKilometerDTO(
                            t.getScooterId(), t.getKilometers(), totalTime);
                })
                .collect(Collectors.toList());
    }

}
