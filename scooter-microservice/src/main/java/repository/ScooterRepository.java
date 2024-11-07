package repository;

import entities.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {

    @Query("SELECT COUNT(m) FROM Scooter m WHERE m.availeable = true AND m.underMaintenance = false")
    long countByAvaileable();

    @Query("SELECT COUNT(m) FROM Scooter m WHERE m.underMaintenance = true")
    long countByUnderMaintenance();

    @Query("SELECT m FROM Scooter m WHERE " +
            "(6371 * acos(cos(radians(:latitud)) * cos(radians(m.latitude)) * " +
            "cos(radians(m.longitude) - radians(:longitud)) + sin(radians(:latitud)) * " +
            "sin(radians(m.latitude)))) < :radio")
    List<Scooter> findClosestScooter(@Param("latitud") double latitud,
                                            @Param("longitud") double longitud,
                                            @Param("radio") double radio);

}
