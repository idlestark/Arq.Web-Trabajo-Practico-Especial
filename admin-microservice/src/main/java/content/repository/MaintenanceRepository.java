package content.repository;

import content.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    @Query("SELECT COUNT(m) > 0 FROM Maintenance m WHERE m.scooterId = :scooterId AND m.finishDate IS NULL")
    boolean isScooterUnderMaintenance(@Param("scooterId") Long scooterId);

    Optional<Maintenance> findByScooterIdAndFinishDateIsNull(Long scooterId);
}
