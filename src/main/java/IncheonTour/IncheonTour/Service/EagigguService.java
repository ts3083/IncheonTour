package IncheonTour.IncheonTour.Service;

import IncheonTour.IncheonTour.Repsotory.EagigguRepository;
import IncheonTour.IncheonTour.Repsotory.PathRepository;
import IncheonTour.IncheonTour.domain.Eagiggu;
import IncheonTour.IncheonTour.domain.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class EagigguService {

    private final EagigguRepository eagigguRepository;
    private final PathRepository pathRepository;

    public Eagiggu getEagigguById(Long id) {
        return eagigguRepository.findById(id).get();
    }

    public void updateEagigguPath(Long eagigguId, Long pathId) {
        Eagiggu eagiggu = eagigguRepository.findById(eagigguId).get();
        Path path = pathRepository.findById(pathId).get();
        eagiggu.setPath(path);
    }
}
