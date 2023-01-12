package IncheonTour.IncheonTour.Service;

import IncheonTour.IncheonTour.domain.Location;
import IncheonTour.IncheonTour.domain.MyPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PathServiceTest {

    @Autowired PathService pathService;
    @Autowired EagigguService eagigguService;

    @Test
    @DisplayName("특정 path에 속한 location 테스트")
    public void path_location_check() throws Exception {
        MyPath myPath = pathService.findAllPath().get(0);
        List<Location> locationList = pathService.findAllPathLocation(myPath.getId());

        assertEquals(4, locationList.size());
    }


}