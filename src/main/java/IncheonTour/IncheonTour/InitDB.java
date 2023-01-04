package IncheonTour.IncheonTour;

import IncheonTour.IncheonTour.Repsotory.EagigguRepository;
import IncheonTour.IncheonTour.Repsotory.LocationRepository;
import IncheonTour.IncheonTour.Repsotory.PathLocationRepository;
import IncheonTour.IncheonTour.Repsotory.PathRepository;
import IncheonTour.IncheonTour.domain.Eagiggu;
import IncheonTour.IncheonTour.domain.Location;
import IncheonTour.IncheonTour.domain.Path;
import IncheonTour.IncheonTour.domain.PathLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final PathLocationRepository pathLocationRepository;
        private final PathRepository pathRepository;
        private final EagigguRepository eagigguRepository;
        private final LocationRepository locationRepository;

        public void dbInit() {

            Eagiggu eagiggu = new Eagiggu(Integer.toUnsignedLong(1), "1", null, null);
            Eagiggu saveEagiggu = eagigguRepository.save(eagiggu);

            Location location1 = new Location(null, "해당화사진관", "37.47742287234143", "126.61770608954193");
            Location location2 = new Location(null, "동화마을슈퍼", "37.477576928286105", "126.61937569483854");
            Location location3 = new Location(null, "사진의도서관(LBDF)", "37.47030931830154", "126.62611607172194");
            Location location4 = new Location(null, "빽투더레트로", "37.47386070236973", "126.62020567205283");
            Location saveLocation1 = locationRepository.save(location1);
            Location saveLocation2 = locationRepository.save(location2);
            Location saveLocation3 = locationRepository.save(location3);
            Location saveLocation4 = locationRepository.save(location4);

            Path path = new Path();
            Path savePath = pathRepository.save(path);

            PathLocation pathLocation1 = PathLocation.createPathLocation(savePath, saveLocation1);
            PathLocation pathLocation2 = PathLocation.createPathLocation(savePath, saveLocation2);
            PathLocation pathLocation3 = PathLocation.createPathLocation(savePath, saveLocation3);
            PathLocation pathLocation4 = PathLocation.createPathLocation(savePath, saveLocation4);
            PathLocation savePathLocation1 = pathLocationRepository.save(pathLocation1);
            PathLocation savePathLocation2 = pathLocationRepository.save(pathLocation2);
            PathLocation savePathLocation3 = pathLocationRepository.save(pathLocation3);
            PathLocation savePathLocation4 = pathLocationRepository.save(pathLocation4);

        }

    }
}
