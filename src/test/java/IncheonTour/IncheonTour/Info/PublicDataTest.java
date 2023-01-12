package IncheonTour.IncheonTour.Info;

import IncheonTour.IncheonTour.Repsotory.LocationRepository;
import IncheonTour.IncheonTour.Service.EagigguService;
import IncheonTour.IncheonTour.Service.PathService;
import IncheonTour.IncheonTour.domain.Location;
import IncheonTour.IncheonTour.domain.MyPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PublicDataTest {

    @Autowired
    LocationRepository locationRepository;
    @Autowired
    EagigguService eagigguService;
    @Autowired
    PathService pathService;

    /*@Test
    @DisplayName("날씨 조회 테스트")
    public void test1() throws Exception {
        MyPath myPath = eagigguService.getEagigguById(Integer.toUnsignedLong(1)).getMyPath();
        List<Location> locations = pathService.findAllPathLocation(myPath.getId()); // 사용자가 선택한 path의 locations();

        Map<String, Pair<String, String>> mapInfo = new HashMap<>();
        locations.forEach(location
                        -> mapInfo.put(location.getSigungu().getName(),
                        Pair.of(location.getSigungu().getNx(), location.getSigungu().getNy())));

        assertEquals(1, mapInfo.size());
        assertEquals("54", mapInfo.get("중구").getLeft());
    }*/

    /*@Test
    @DisplayName("getBaseDate와 getBaseTime 테스트")
    public void test2() throws Exception {
        String baseTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH00"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String baseDate = sdf.format(c1.getTime());

        assertEquals("1800", baseTime);
        assertEquals("20230109", baseDate);
    }*/

}