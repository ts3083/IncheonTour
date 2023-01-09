package IncheonTour.IncheonTour.Repsotory;

import IncheonTour.IncheonTour.domain.Sigungu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SigunguRepositoryTest {

    @Autowired SigunguRepository sigunguRepository;

    @Test
    @DisplayName("name으로 Sigungu 조회가 가능한지 테스트")
    public void Sigungu조회_by_name() throws Exception {
        Optional<Sigungu> sigungu = sigunguRepository.findByName("중구");

        assertTrue(sigungu.isPresent());

        assertEquals("10", sigungu.get().getCode());
    }
}