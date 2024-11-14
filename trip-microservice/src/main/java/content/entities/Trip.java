package content.entities;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long scooterId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double kilometers;
    private boolean inProgress;
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("trip")
    private List<Pause> pauses;

    public Trip() {
        super();
        this.pauses = new ArrayList<>();
    }

    public Trip(Long scooterId, LocalDateTime startDate, LocalDateTime endDate, double kilometers) {
        super();
        this.scooterId = scooterId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.kilometers = kilometers;
        this.inProgress = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScooterId() {
        return scooterId;
    }

    public void setScooterId(Long scooterId) {
        this.scooterId = scooterId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public List<Pause> getPauses() { return pauses; }

    public void setPauses(List<Pause> pauses) {
        this.pauses = pauses;
    }

    public double getTimeUsed() {
        if (startDate != null && endDate != null) {
            return Duration.between(startDate, endDate).toMinutes();
        }
        return 0;
    }
}