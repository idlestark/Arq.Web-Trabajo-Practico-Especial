package content.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TripDTO {
    private Long id;
    private Long scooterId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double kilometers;
    private boolean inProgress;
    @OneToMany(mappedBy = "tripDTO", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("TO")
    private List<StopDTO> pauses;

    public TripDTO(Long id, Long scooterId, LocalDateTime endDate, LocalDateTime startDate, double kilometers, boolean inProgress, List<StopDTO> pauses) {
        this.id = id;
        this.scooterId = scooterId;
        this.endDate = endDate;
        this.startDate = startDate;
        this.kilometers = kilometers;
        this.inProgress = inProgress;
        this.pauses = pauses;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getScooterId() { return scooterId; }

    public void setScooterId(Long scooterId) { this.scooterId = scooterId; }

    public LocalDateTime getStartDate() { return startDate; }

    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }

    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public double getKilometers() { return kilometers; }

    public void setKilometers(double kilometers) { this.kilometers = kilometers; }

    public boolean isInProgress() { return inProgress; }

    public void setInProgress(boolean inProgress) { this.inProgress = inProgress; }

    public List<StopDTO> getPauses() { return pauses; }

    public void setPauses(List<StopDTO> pauses) { this.pauses = pauses; }

    public double getUseTime() {
        if (this.startDate != null && this.endDate != null) {
            return Duration.between(startDate, endDate).toMinutes();
        }
        return 0;
    }
}
