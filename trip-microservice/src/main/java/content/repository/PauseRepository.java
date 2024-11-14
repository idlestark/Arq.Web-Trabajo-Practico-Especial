package content.repository;

import content.entities.Pause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PauseRepository extends JpaRepository<Pause, Long> {

}