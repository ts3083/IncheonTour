package IncheonTour.IncheonTour.Repsotory;

import IncheonTour.IncheonTour.domain.Sigungu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SigunguRepository extends JpaRepository<Sigungu, Long> {

    Optional<Sigungu> findByName(String name);
}
