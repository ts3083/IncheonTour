package IncheonTour.IncheonTour;

import IncheonTour.IncheonTour.Repsotory.*;
import IncheonTour.IncheonTour.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

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

            Sigungu s1 = Sigungu.createSigungu("강화군", "1", "51", "130");
            Sigungu s2 = Sigungu.createSigungu("계양구", "2", "56", "126");
            Sigungu s3 = Sigungu.createSigungu("미추홀구", "3", "54", "124");
            Sigungu s4 = Sigungu.createSigungu("남동구", "4", "56", "124");
            Sigungu s5 = Sigungu.createSigungu("동구", "5", "54", "125");
            Sigungu s6 = Sigungu.createSigungu("부평구", "6", "55", "125");
            Sigungu s7 = Sigungu.createSigungu("서구", "7", "55", "126");
            Sigungu s8 = Sigungu.createSigungu("연수구", "8", "55", "123");
            Sigungu s9 = Sigungu.createSigungu("옹진군", "9", "54", "124");
            Sigungu s10 = Sigungu.createSigungu("중구", "10", "54", "125");

            List<Sigungu> sigungus = Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10);
            sigunguRepository.saveAll(sigungus);

            String imagePath = "C:/Back-End/IncheonTour/src/main/resources/image/";

            // 필름카메라 들고 찰칵 패스 location
            Location l1 = Location.createLocation("해당화사진관",
                    imagePath, "l1.jpg", s10, "37.47742287234143", "126.61770608954193",
                    "필름카메라 대여패키지를 통해 저렴한 가격으로 필름카메라를 경험할 수 있는 사진관");
            Location l2 = Location.createLocation("동화마을슈퍼",
                    imagePath, "l2.jpg", s10, "37.477576928286105", "126.61937569483854",
                    "40년동안 한 자리를 지키며 운영해 온 전통 깊은 슈퍼");
            Location l3 = Location.createLocation( "사진의도서관(LBDF)",
                    imagePath, "l3.jpg", s10, "37.47030931830154", "126.62611607172194",
                    "책과 어울리는 커피와 차를 마시며 시간을 보낼 수 있는 장소");
            Location l4 = Location.createLocation( "빽투더레트로",
                    imagePath, "l4.jpg", s10, "37.47386070236973", "126.62020567205283",
                    "개항장에 위치한 레트로 감성 오락실");
            // 걸어서 센트럴파크 한바퀴 패스 location
            Location l5 = Location.createLocation("송도센트럴파크 호텔",
                    imagePath, "l5.jpg", s8, "37.390343971178275", "126.63751237584167",
                    "송도 국제도시 핵심지구에 위치한 송도를 대표하는 호텔, 송도 센트럴파크호텔입니다.");
            Location l6 = Location.createLocation("Burger Room 181",
                    imagePath, "l6.jpg", s8, "37.392318171331524", "126.64450999421724",
                    "송도 센트럴파크 근처 버거 맛집");
            Location l7 = Location.createLocation("송도센트럴파크",
                    imagePath, "l7.jpg", s8, "37.39266755763194", "126.63864457902064",
                    "송도국제도시 도심 한가운데 있는 거대한 공원");
            Location l8 = Location.createLocation("트라이보울",
                    imagePath, "l8.jpg", s8, "37.39421069165456", "126.63502341465761",
                    "인천을 대표하는 복합문화시설");
            List<Location> locations = Arrays.asList(l1, l2, l3, l4, l5, l6, l7, l8);
            locationRepository.saveAll(locations);

            MyPath p1 = MyPath.createPath("필름카메라 들고 찰칵 패스");
            MyPath p2 = MyPath.createPath("걸어서 센트럴파크 한바퀴 패스");
            List<MyPath> myPaths = Arrays.asList(p1, p2);
            pathRepository.saveAll(myPaths);

            PathLocation pl1 = PathLocation.createPathLocation(p1, l1);
            PathLocation pl2 = PathLocation.createPathLocation(p1, l2);
            PathLocation pl3 = PathLocation.createPathLocation(p1, l3);
            PathLocation pl4 = PathLocation.createPathLocation(p1, l4);
            PathLocation pl5 = PathLocation.createPathLocation(p2, l5);
            PathLocation pl6 = PathLocation.createPathLocation(p2, l6);
            PathLocation pl7 = PathLocation.createPathLocation(p2, l7);
            PathLocation pl8 = PathLocation.createPathLocation(p2, l8);
            List<PathLocation> pathLocations = Arrays.asList(pl1, pl2, pl3, pl4, pl5, pl6, pl7, pl8);
            pathLocationRepository.saveAll(pathLocations);
        }
    }
}
