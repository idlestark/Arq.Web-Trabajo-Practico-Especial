package content.DTO;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

public class PauseDTO {

    private Long id;

    private LocalDateTime start;
    private LocalDateTime end;
    private double duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tripDTO_id", nullable = false)
    private TripDTO trip;

    public PauseDTO() {
    }

    public PauseDTO(Long id, LocalDateTime start, LocalDateTime end, double duration, TripDTO tripdto) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.duration = duration;
        this.trip = tripdto;
    }


    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public LocalDateTime getStart() {return start;}

    public void setStart(LocalDateTime start) {this.start = start;}

    public LocalDateTime getEnd() {return end;}

    public void setEnd(LocalDateTime end) {this.end = end;}

    public double getDuration() {return duration;}

    public void setDuration(double duration) {this.duration = duration;}

    public TripDTO getTrip() {return trip;}

    public void setTrip(TripDTO trip) {this.trip = trip;}
}