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

    private static long pauseMaxTime = 15;
    private static double extraFee = 10.0;
    private static double kilometerCost = 7.5;
    private final ContentNegotiatingViewResolver viewResolver;

    @Transactional(readOnly = true)
    public List<Trip> findAllTrips() {
        return tripRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Trip findTripById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }

    @Transactional
    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    @Transactional
    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }

    @Transactional
    public Trip updateTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    @Transactional(readOnly = true)
    public Double getTotalTimeWithPauses(Long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));

        double totalPauseInMinutes = 0;

        for (Pause pause : trip.getPauses()) {
            if (pause.getEndDate() != null) {
                long durationInMinutes = Duration.between(pause.getStartDate(), pause.getEndDate()).toMinutes();
                totalPauseInMinutes += durationInMinutes;
            }
        }

        return trip.getTimeUsed() + totalPauseInMinutes;
    }

    public List<KilometersReportDTO> getKilometersReport() {
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

    @Transactional
    public Trip endTrip(Long tripId, double kilometers) {

        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));

        if (!trip.isInProgress()) {
            throw new RuntimeException("Trip has already ended");
        }

        trip.setEndDate(LocalDateTime.now());
        trip.setKilometers(kilometers);
        trip.setInProgress(false);
        tripRepository.save(trip);

        return trip;
    }

    public double calculateTripCost(long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
        double total = 0.0;

        if (mustApplyExtraFee(trip)) {
            Duration time = getIncreasedPauseDuration(trip.getPauses());

            if (time != null) {
                double minutes = time.toMinutes() + (time.getSeconds() % 60) / 60.0;
                total += minutes * extraFee;
            }
        }
        total += trip.getTimeUsed() * kilometerCost;
        return total;
    }

    public boolean mustApplyExtraFee(Trip trip) {
        return trip.getPauses().stream().anyMatch(pause -> pause.getDuration() > pauseMaxTime);
    }

    public Duration getIncreasedPauseDuration(List<Pause> pauses) {
        for (Pause pause : pauses) {
            if (pause.getDuration() > pauseMaxTime) {
                return Duration.ofMinutes(pause.getDuration()).plusMinutes(15);
            }
        }
        return null;
    }

}