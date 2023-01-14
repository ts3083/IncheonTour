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
                    "필름카메라 대여패키지를 통해 저렴한 가격으로 필름카메라를 경험할 수 있는 사진관입니다." +
                            " 인천역 2번 출구에서 20m 안에 위치하고 있습니다. 건물 1층에 위치한 해당화사진관은 " +
                            "다른 현상소와 달리 계단이 없어 접근성이 용이합니다. 주차는 인근 차이나타운에 주차하실" +
                            " 수 있습니다. 해당화사진관의 영업시간은 13시부터 20시까지 입니다.(정기휴무일은 " +
                            "월요일, 화요일입니다.)");
            Location l2 = Location.createLocation("동화마을슈퍼",
                    imagePath, "l2.jpg", s10, "37.477576928286105", "126.61937569483854",
                    "40년동안 한 자리를 지키며 운영해 온 전통 깊은 슈퍼입니다. 자유공원 꼭대기에 위치하고 있습니다. " +
                            "추억의 피카츄, 떡꼬치 이외에도 음료수, 커피, 불량식품 등등 다양하게 판매하고 있습니다. " +
                            "어린 시절 먹던 추억의 맛을 느끼고 싶은 사람들에게 추천합니다.");
            Location l3 = Location.createLocation( "사진의도서관(LBDF)",
                    imagePath, "l3.jpg", s10, "37.47030931830154", "126.62611607172194",
                    "책과 어울리는 커피와 차를 마시며 시간을 보낼 수 있는 장소입니다. LBDF 사진의 도서관은 사진을 " +
                            "주매체로 하는 작가들의 레퍼런스와 시각예술 관련 서적을 공유하는 공간입니다. 사진 직가들의 영감을 준 책," +
                            " 사진관련 도서를 모아놓은 이카이브 도서관입니다. 이용방법은 1인 입장료 5천원 음료포함이고 영업시간은 " +
                            "13시부터 19시까지 입니다. (정기휴무일은 일요일, 월요일입니다.)");
            Location l4 = Location.createLocation( "빽투더레트로",
                    imagePath, "l4.jpg", s10, "37.47386070236973", "126.62020567205283",
                    "개항장에 위치한 레트로 감성 오락실입니다. 인천역 1번 출구에서 도보 8~9분 거리에 위치하며 추억의 " +
                            "오락실 컨셉으로 주말에는 항상 붐비는 곳입니다. 영업시간은 10시부터 21시까지 입니다");
            // 걸어서 센트럴파크 한바퀴 패스 location
            Location l5 = Location.createLocation("송도센트럴파크 호텔",
                    imagePath, "l5.jpg", s8, "37.390343971178275", "126.63751237584167",
                    "송도 국제도시 핵심지구에 위치한 송도를 대표하는 송도 센트럴파크호텔입니다. 1일 숙박비 평균" +
                            " 10-11만원 정도이며 주차공간은 여유가 있는 편입니다. 1호선 센트럴파크역 도보 10분이내 위치하고 " +
                            "센트럴파크 도보 5분이내 위치합니다. 가격대비 좋은 시설과 지하철역, 센트럴파크, 아울렛 등이 가깝게 " +
                            "위치하고 있어 송도 여행 시 숙박하기 좋은 곳 입니다.");
            Location l6 = Location.createLocation("Burger Room 181",
                    imagePath, "l6.jpg", s8, "37.392318171331524", "126.64450999421724",
                    "송도 센트럴파크 수제 버거 맛집입니다. 181룸, 치즈룸, 로스티드치즈룸, 바베큐어니언룸과 같은 수제버거와" +
                            " 갓 튀겨낸 감자튀김과 어니언링이 맛있는 집입니다. 맥주 종류도 다양하게 판매하고 있어 저녁시간대" +
                            " 버거에 맥주가 끌리신다면 추천하는 맛집입니다.");
            Location l7 = Location.createLocation("송도센트럴파크",
                    imagePath, "l7.jpg", s8, "37.39266755763194", "126.63864457902064",
                    "송도국제도시 도심 한가운데 있는 거대한 공원입니다. 인천 도시철도 1호선 센트럴파크역에 자리잡고 있고, " +
                            "주차장도 매우 넓어 교통이 매우 편리합니다. 센트럴파크의 규모는 약 14만평으로 광활한 넓이를 " +
                            "자랑합니다. 축구장의 56배 면적에 해당하는 크기로 여의도 공원 두배 규모입니다. 송도코마린이스트보트하우스" +
                            "라는 곳에서 출발하는 보트는 문보트와 구르미보트, 패밀리보트가 있습니다. 가격대는 32,000원에서 37,000원정도" +
                            "됩니다. 시민들을 위한 공원의 역할 뿐만 아니라 여행자들도 편안하게 거닐며 구경할 수 있는 멋진 장소입니다.");
            Location l8 = Location.createLocation("트라이보울",
                    imagePath, "l8.jpg", s8, "37.39421069165456", "126.63502341465761",
                    "인천을 대표하는 복합문화시설입니다. 도자기를 빚어 놓은듯 아름다운 곡선과 긴장감을 주는 외형을 가지고 있습니다. " +
                            "시민들을 위한 다양한 공연이나 전시, 교육프로그램을 운영하는 곳입니다. 원형극장 형태의 공연장에는 문화예술교육과 " +
                            "전시 등이 가능한 다목적 공간으로 구성되어 있습니다. 400여명을 수용할 수 있는 곳으로 음악회나 재즈, 뮤지컬, " +
                            "국악 등의 공연을 즐길 수 있는 장소입니다.");
            List<Location> locations = Arrays.asList(l1, l2, l3, l4, l5, l6, l7, l8);
            locationRepository.saveAll(locations);

            MyPath p1 = MyPath.createPath("필름카메라 들고 찰칵 패스");
            MyPath p2 = MyPath.createPath("걸어서 센트럴파크 한바퀴 패스");
            MyPath p3 = MyPath.createPath("제로웨이스트 패스");
            MyPath p4 = MyPath.createPath("공정여행 패스");
            MyPath p5 = MyPath.createPath("견생샷 핫플패스");
            MyPath p6 = MyPath.createPath("동인천 낭만시장 패스");
            MyPath p7 = MyPath.createPath("걸어야만 보이는 낭만시장 패스");
            MyPath p8 = MyPath.createPath("영종 해안 드라이브 패스");
            List<MyPath> myPaths = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8);
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
