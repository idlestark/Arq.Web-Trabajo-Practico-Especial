package content.repository;
import content.entities.Ticket;
import content.entities.TicketDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT SUM(t.amount) FROM Ticket t WHERE YEAR(t.dateOfIssue) = :year AND MONTH(t.dateOfIssue) BETWEEN :startMonth AND :endMonth")
    double getTotal(@Param("year") int year, @Param("monthStart") int startMonth, @Param("monthEnd") int endMonth);

}
