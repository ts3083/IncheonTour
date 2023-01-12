package IncheonTour.IncheonTour.Service;

import IncheonTour.IncheonTour.Repsotory.EagigguRepository;
import IncheonTour.IncheonTour.Repsotory.LocationRepository;
import IncheonTour.IncheonTour.Repsotory.PathRepository;
import IncheonTour.IncheonTour.domain.Eagiggu;
import IncheonTour.IncheonTour.domain.Location;
import IncheonTour.IncheonTour.domain.MyPath;
import IncheonTour.IncheonTour.dto.LocationDto;
import IncheonTour.IncheonTour.dto.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EagigguService {

    private final EagigguRepository eagigguRepository;
    private final PathRepository pathRepository;
    private final PathService pathService;
    private final LocationRepository locationRepository;

    public Eagiggu getEagigguById(Long id) {
        return eagigguRepository.findById(id).get();
    }

    @Transactional
    public ResponseResult<List> updateEagigguPath(Long eagigguId, Long pathId) {
        Eagiggu eagiggu = eagigguRepository.findById(eagigguId).get();
        MyPath myPath = pathRepository.findById(pathId).get();
        eagiggu.setMyPath(myPath);

        List<Location> locationList = pathService.findAllPathLocation(pathId);
        List<LocationDto> locationDtos = locationList.stream()
                .map(location -> new LocationDto(location)).collect(Collectors.toList());

        ResponseResult<List> listResponseResult = new ResponseResult<>();
        listResponseResult.setPathName(myPath.getName());
        listResponseResult.setData(locationDtos);

        return listResponseResult;
    }

    @Transactional
    public String updateEagigguCurrentLocation(Long eagigguId, Location location) {
        Eagiggu eagiggu = eagigguRepository.findById(eagigguId).get();
        eagiggu.setCurrent_location(location);
        return location.getName();
    }

    @Transactional
    public String updateEagigguCurrentLocationNull(Long eagigguId) {
        Eagiggu eagiggu = eagigguRepository.findById(eagigguId).get();
        eagiggu.setCurrent_location(null);
        return "out";
    }
}
