package content.entities;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Fee {

    public Fee(FeeType feeType, double newBaseFee, LocalDate startDate, Object o) {
    }

    public enum FeeType {
        BASE,
        EXTRA_PAUSE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private FeeType type;
    private double amount;
    private LocalDate startDate;
    private LocalDate endDate;

    public Fee() {
        super();
    }

    public Fee(LocalDate endDate, LocalDate startDate, double amount, FeeType type) {
        this.endDate = endDate;
        this.startDate = startDate;
        this.amount = amount;
        this.type = type;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public FeeType getType() { return type; }

    public void setType(FeeType type) { this.type = type; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public LocalDate getStartDate() { return startDate; }

    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }

    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}