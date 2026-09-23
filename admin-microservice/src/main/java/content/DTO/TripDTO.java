package content.DTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TripDTO {
    private Long id;
    private Long scooterId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double kilometers;
    private boolean inProgress;
    private List<PauseDTO> pauses = new ArrayList<>();

    public TripDTO() {}

    public TripDTO(Long id, Long scooterId, LocalDateTime startDate, LocalDateTime endDate, double kilometers, boolean inProgress, List<PauseDTO> pauses) {
        this.id = id;
        this.scooterId = scooterId;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public List<PauseDTO> getPauses() { return pauses; }
    public void setPauses(List<PauseDTO> pauses) { this.pauses = pauses; }

    public double getUseTime() {
        if (this.startDate != null && this.endDate != null) {
            return Duration.between(startDate, endDate).toMinutes();
        }
        return 0;
    }
}
