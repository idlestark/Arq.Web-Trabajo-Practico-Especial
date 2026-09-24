package content.entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long scooterId;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;


    public Maintenance(Long scooterId, String description, LocalDateTime startDate, LocalDateTime finishDate) {
        this.scooterId = scooterId;
        this.description = description;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public Maintenance() {
        super();
    }


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getScooterId() { return scooterId; }

    public void setScooterId(Long scooterId) { this.scooterId = scooterId; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getStartDate() { return startDate; }

    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getFinishDate() { return finishDate; }

    public void setFinishDate(LocalDateTime finishDate) { this.finishDate = finishDate; }
}
