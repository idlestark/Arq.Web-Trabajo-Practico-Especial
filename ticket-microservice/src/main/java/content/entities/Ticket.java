package content.entities;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private double amount;
    private LocalDate dateOfIssue;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketDetails> ticketDetails;

    public Ticket(){
        this.ticketDetails = new ArrayList<>();
    }

    public Ticket(Long id, Long userId, double amount, LocalDate dateOfIssue, List<TicketDetails> ticketDetails) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.dateOfIssue = dateOfIssue;
        this.ticketDetails = ticketDetails;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public LocalDate getDateOfIssue() { return dateOfIssue; }

    public void setDateOfIssue(LocalDate date) { this.dateOfIssue = date; }

    public List<TicketDetails> getTicketDetails() { return ticketDetails; }

    public void setTicketDetails(List<TicketDetails> ticketDetails) { this.ticketDetails = ticketDetails; }

    public double calculateTotalAmount(){
        return ticketDetails.stream().mapToDouble(TicketDetails::getAmount).sum();
    }

    public void addDetails(TicketDetails details){
        details.setTicket(this);
        ticketDetails.add(details);
        this.amount = calculateTotalAmount();
    }

}
