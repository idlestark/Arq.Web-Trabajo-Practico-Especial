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
    private Long duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public Pause() {
        super();
    }

    public Pause(Trip trip, Long duration, LocalDateTime endDate, LocalDateTime startDate) {
        this.trip = trip;
        this.duration = duration;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getDuration() { return duration; }

    public void setDuration(Long duration) { this.duration = duration; }

    public LocalDateTime getEndDate() { return endDate; }

    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public LocalDateTime getStartDate() { return startDate; }

    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public void setTrip(Trip trip) { this.trip = trip; }

    public Long getTripId() { return trip.getId(); }

}