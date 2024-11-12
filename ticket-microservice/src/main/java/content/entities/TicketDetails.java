package content.entities;
import jakarta.persistence.*;

@Entity
public class TicketDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;
    private Long tripId;
    private double feeBase;
    private double feeExtra;
    private long timeUsed;
    private long timePaused;
    private double amount;

    public TicketDetails() {
        super();
    }

    public TicketDetails(Long id, Ticket ticket, long tripId, double feeBase, double feeExtra, long timeUsed, long timePaused, double amount) {
        this.id = id;
        this.ticket = ticket;
        this.tripId = tripId;
        this.feeBase = feeBase;
        this.feeExtra = feeExtra;
        this.timeUsed = timeUsed;
        this.timePaused = timePaused;
        this.amount = amount;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Ticket getTicket() { return ticket; }

    public void setTicket(Ticket ticket) { this.ticket = ticket; }

    public long getTripId() { return tripId; }

    public void setTripId(long tripId) { this.tripId = tripId; }

    public double getFeeBase() { return feeBase; }

    public void setFeeBase(double feeBase) { this.feeBase = feeBase; }

    public double getFeeExtra() { return feeExtra; }

    public void setFeeExtra(double feeExtra) { this.feeExtra = feeExtra; }

    public long getTimeUsed() { return timeUsed; }

    public void setTimeUsed(long timeUsed) { this.timeUsed = timeUsed; }

    public long getTimePaused() { return timePaused; }

    public void setTimePaused(long timePaused) { this.timePaused = timePaused; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public void calculateAmount() {
        double basePrice = feeBase * timeUsed;
        double extraPrice = timePaused > 15 ? feeExtra * (timePaused - 15) : 0;
        this.amount = basePrice + extraPrice;
    }

}
