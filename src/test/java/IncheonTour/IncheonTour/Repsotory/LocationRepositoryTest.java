package IncheonTour.IncheonTour.Repsotory;

import IncheonTour.IncheonTour.domain.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LocationRepositoryTest {

    @Autowired LocationRepository locationRepository;

    @Test
    @DisplayName("name으로 location 조회가 가능한지 테스트")
    public void location조회_by_name() throws Exception {
        List<Location> locations = locationRepository.findByName("해당화사진관");
        Optional<Location> location = locations.stream().filter(l -> Objects.equals(l.getName(), "해당화사진관")).findAny();

        assertEquals(location.get().getName(), "해당화사진관");
    }
}