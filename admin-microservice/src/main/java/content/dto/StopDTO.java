package content.dto;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

public class StopDTO {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double duration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tripDTO_id", nullable = false)
    private TripDTO trip;

    public StopDTO() {
    }

    public StopDTO(Long id, LocalDateTime endDate, LocalDateTime startDate, double duration, TripDTO trip) {
        this.id = id;
        this.endDate = endDate;
        this.startDate = startDate;
        this.duration = duration;
        this.trip = trip;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDateTime getStartDate() { return startDate; }

    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }

    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public double getDuration() { return duration; }

    public void setDuration(double duration) { this.duration = duration; }

    public TripDTO getTrip() { return trip; }

    public void setTrip(TripDTO trip) { this.trip = trip; }
}