package content.entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pause {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public Pause() {
        super();
    }

    public Pause(Trip trip, double duration, LocalDateTime endDate, LocalDateTime startDate, Long id) {
        this.trip = trip;
        this.duration = duration;
        this.endDate = endDate;
        this.startDate = startDate;
        this.id = id;
    }

    public void setViaje(Trip trip) { this.trip = trip; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public double getDuration() { return duration; }

    public void setDuration(double duration) { this.duration = duration; }

    public LocalDateTime getEndDate() { return endDate; }

    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public LocalDateTime getStartDate() { return startDate; }

    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public Trip getTrip() { return trip; }

    public void setTrip(Trip trip) { this.trip = trip; }
}