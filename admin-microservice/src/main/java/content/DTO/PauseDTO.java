package content.DTO;

import java.time.LocalDateTime;

public class PauseDTO {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long duration;

    public PauseDTO() {}

    public PauseDTO(Long id, LocalDateTime startDate, LocalDateTime endDate, Long duration) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public Long getDuration() { return duration; }
    public void setDuration(Long duration) { this.duration = duration; }
}