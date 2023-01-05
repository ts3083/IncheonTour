package IncheonTour.IncheonTour.Service;

import IncheonTour.IncheonTour.Repsotory.PathRepository;
import IncheonTour.IncheonTour.domain.Location;
import IncheonTour.IncheonTour.domain.Path;
import IncheonTour.IncheonTour.domain.PathLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PathService {

    private final PathRepository pathRepository;

    public List<Location> findAllPathLocation(Long pathId) {
        List<Location> results = new ArrayList<>();

        List<PathLocation> pathLocations = pathRepository.findById(pathId).get().getPathLocations();
        pathLocations.stream().forEach(pl -> results.add(pl.getLocation()));
        return results;
    }

    public List<Path> findAllPath() {
        return pathRepository.findAll();
    }
}
