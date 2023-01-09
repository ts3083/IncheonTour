package IncheonTour.IncheonTour;

import IncheonTour.IncheonTour.Repsotory.*;
import IncheonTour.IncheonTour.domain.*;
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
        private final SigunguRepository sigunguRepository;
        public void dbInit() {

            Eagiggu eagiggu = new Eagiggu(Integer.toUnsignedLong(1), "1", 0, 0,
                    null, null);
            Eagiggu saveEagiggu = eagigguRepository.save(eagiggu);

            Sigungu sigungu1 = Sigungu.createSigungu("강화군", "1", "51", "130");
            Sigungu sigungu2 = Sigungu.createSigungu("계양구", "2", "56", "126");
            Sigungu sigungu3 = Sigungu.createSigungu("미추홀구", "3", "54", "124");
            Sigungu sigungu4 = Sigungu.createSigungu("남동구", "4", "56", "124");
            Sigungu sigungu5 = Sigungu.createSigungu("동구", "5", "54", "125");
            Sigungu sigungu6 = Sigungu.createSigungu("부평구", "6", "55", "125");
            Sigungu sigungu7 = Sigungu.createSigungu("서구", "7", "55", "126");
            Sigungu sigungu8 = Sigungu.createSigungu("연수구", "8", "55", "123");
            Sigungu sigungu9 = Sigungu.createSigungu("옹진군", "9", "54", "124");
            Sigungu sigungu10 = Sigungu.createSigungu("중구", "10", "54", "125");

            sigunguRepository.save(sigungu1);
            sigunguRepository.save(sigungu2);
            sigunguRepository.save(sigungu3);
            sigunguRepository.save(sigungu4);
            sigunguRepository.save(sigungu5);
            sigunguRepository.save(sigungu6);
            sigunguRepository.save(sigungu7);
            sigunguRepository.save(sigungu8);
            sigunguRepository.save(sigungu9);
            sigunguRepository.save(sigungu10);

            Location location1 = Location.createLocation("해당화사진관", sigungu10,
                    "37.47742287234143", "126.61770608954193");
            Location location2 = Location.createLocation("동화마을슈퍼", sigungu10,
                    "37.477576928286105", "126.61937569483854");
            Location location3 = Location.createLocation( "사진의도서관(LBDF)", sigungu10,
                    "37.47030931830154", "126.62611607172194");
            Location location4 = Location.createLocation( "빽투더레트로", sigungu10,
                    "37.47386070236973", "126.62020567205283");
            Location saveLocation1 = locationRepository.save(location1);
            Location saveLocation2 = locationRepository.save(location2);
            Location saveLocation3 = locationRepository.save(location3);
            Location saveLocation4 = locationRepository.save(location4);

            Path path = new Path();
            path.setName("필름카메라 들고 찰칵 패스");
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
