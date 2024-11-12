package content.service;
import content.DTO.KilometersReportDTO;
import content.entities.Pause;
import content.entities.Trip;
import content.repository.TripRepository;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import java.util.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TripService {

    private final TripRepository tripRepository;

    private static long maxTimePaused = 15; //
    private static double extraFee = 10.0; //
    private static double kilometerCost = 7.5;
    private final ContentNegotiatingViewResolver viewResolver;

    @Transactional(readOnly = true)
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Trip findById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }

    @Transactional
    public Trip save(Trip trip) {
        return tripRepository.save(trip);
    }

    @Transactional
    public void delete(Long id) {
        tripRepository.deleteById(id);
    }

    @Transactional
    public Trip update(Trip trip) {
        return tripRepository.save(trip);
    }

    @Transactional(readOnly = true)
    public Double getTotalTimeWithPauses(Long tripid) {
        Trip trip = tripRepository.findById(tripid)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        double totalPauseInMinutes = 0;

        for (Pause pause : trip.getPauses()) {
            if (pause.getEndDate() != null) {
                long durationInMinutes = Duration.between(pause.getStartDate(), pause.getEndDate()).toMinutes();
                totalPauseInMinutes += durationInMinutes;
            }
        }

        return trip.getTiempoUso() + totalPauseInMinutes;
    }



    @Transactional
    public Trip endTrip(Long tripId, double kilometers) {

        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));

        if (!trip.isinProgress()) {
            throw new RuntimeException("Trip has already ended");
        }

        trip.setEndDate(LocalDateTime.now());
        trip.setKilometers(kilometers);
        trip.setInProgress(false);
        tripRepository.save(trip);

        return trip;
    }


    public List<KilometersReportDTO> generateKilometersReport() {
        Map<Long, Double> scooterKilometers = new HashMap<>();
        List<Trip> trips = tripRepository.findAll();

        for (Trip trip : trips) {
            scooterKilometers.merge(trip.getScooterId(), trip.getKilometers(), Double::sum);
        }

        List<KilometersReportDTO> reports = new ArrayList<>();
        for (Map.Entry<Long, Double> entry : scooterKilometers.entrySet()) {
            reports.add(new KilometersReportDTO(entry.getKey(), entry.getValue()));
        }

        return reports;
    }



}