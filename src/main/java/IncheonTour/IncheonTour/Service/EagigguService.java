package IncheonTour.IncheonTour.Service;

import IncheonTour.IncheonTour.Repsotory.EagigguRepository;
import IncheonTour.IncheonTour.Repsotory.PathRepository;
import IncheonTour.IncheonTour.domain.Eagiggu;
import IncheonTour.IncheonTour.domain.Location;
import IncheonTour.IncheonTour.domain.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EagigguService {

    private final EagigguRepository eagigguRepository;
    private final PathRepository pathRepository;

    public Eagiggu getEagigguById(Long id) {
        return eagigguRepository.findById(id).get();
    }

    @Transactional
    public void updateEagigguPath(Long eagigguId, Long pathId) {
        Eagiggu eagiggu = eagigguRepository.findById(eagigguId).get();
        Path path = pathRepository.findById(pathId).get();
        eagiggu.setPath(path);
    }

    @Transactional
    public String updateEagigguCurrentLocation(Long eagigguId, Location location) {
        Eagiggu eagiggu = eagigguRepository.findById(eagigguId).get();
        eagiggu.setCurrent_location(location);
        return location.getName();
    }

    @Transactional
    public String updateEagigguCurrentLocationNull(Long eagigguId) {
        Eagiggu eagiggu = eagigguRepository.findById(eagigguId).get();
        eagiggu.setCurrent_location(null);
        return "out";
    }
}
