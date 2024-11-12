package content.repository;
import content.entities.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {

    @Query("SELECT COUNT(m) FROM Scooter m WHERE m.availeable = true AND m.underMaintenance = false")
    long countByOperativeAndAvaileable();

    @Query("SELECT COUNT(m) FROM Scooter m WHERE m.underMaintenance = true")
    long countByUnderMaintenance();

    @Query("SELECT m FROM Scooter m WHERE " +
            "(6371 * acos(cos(radians(:latitude)) * cos(radians(m.latitude)) * " +
            "cos(radians(m.longitude) - radians(:longitude)) + sin(radians(:latitude)) * " +
            "sin(radians(m.latitude)))) < :radius")
    List<Scooter> findNearbyScooters(@Param("latitude") double latitude,
                                          @Param("longitude") double longitude,
                                          @Param("radius") double radius);
}
