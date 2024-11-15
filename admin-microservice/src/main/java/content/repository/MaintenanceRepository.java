package content.repository;
import content.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    @Query("SELECT COUNT(m) > 0 FROM Maintenance m WHERE m.scooterId =:scooterId")
    boolean findByScooter(Long scooterId);

}
