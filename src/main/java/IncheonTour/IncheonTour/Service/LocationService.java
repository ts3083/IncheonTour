package IncheonTour.IncheonTour.Service;

import IncheonTour.IncheonTour.Repsotory.LocationRepository;
import IncheonTour.IncheonTour.domain.Location;
import IncheonTour.IncheonTour.dto.GpsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final EagigguService eagigguService;

    @Transactional
    public String updateCurrentLocation(GpsDto gpsDto) {
        String str = "NULL";

        List<Location> locations = locationRepository.findAll();
        for (int i = 0; i < locations.size(); i++) {
            Location location = locations.get(i);
            double dist = getDistance(
                    Double.parseDouble(gpsDto.getGps_latitude()),
                    Double.parseDouble(gpsDto.getGps_longitude()),
                    Double.parseDouble(location.getGps_latitude()),
                    Double.parseDouble(location.getGps_longitude())); // 해당 location과 현위치와의 거리(meter)
            if (dist < 30) { // 30m 안으로 들어오면 eagiggu의 current_location을 갱신
                str = eagigguService.updateEagigguCurrentLocation(Integer.toUnsignedLong(1), location) + " 도착";
                break;
            }
            else { // 30m 보다 멀어짐
                str = eagigguService.updateEagigguCurrentLocationNull(Integer.toUnsignedLong(1));
            }
        }

        return str;
    }

    public double getDistance(double lat1, double lon1, double lat2, double lon2) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))* Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1609.344;

        return dist; //단위 meter
    }

    //10진수를 radian(라디안)으로 변환
    private static double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }
    //radian(라디안)을 10진수로 변환
    private static double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }
}
