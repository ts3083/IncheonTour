package IncheonTour.IncheonTour.Repsotory;

import IncheonTour.IncheonTour.domain.MyPath;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathRepository extends JpaRepository<MyPath, Long> {
}
