package content.repository;

import content.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByScooterId(Long scooterId);

    @Query("SELECT t.scooterId FROM Trip t WHERE YEAR(t.startDate) = :year GROUP BY t.scooterId HAVING COUNT(t) >= :minTrips")
    List<Long> findScooterIdsWithMinTripsInYear(@Param("minTrips") long minTrips, @Param("year") int year);
}
