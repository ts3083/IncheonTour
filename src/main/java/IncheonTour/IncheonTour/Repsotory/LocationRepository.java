package IncheonTour.IncheonTour.Repsotory;

import IncheonTour.IncheonTour.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
