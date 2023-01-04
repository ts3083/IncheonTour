package IncheonTour.IncheonTour.api;

import IncheonTour.IncheonTour.Repsotory.LocationRepository;
import IncheonTour.IncheonTour.Service.EagigguService;
import IncheonTour.IncheonTour.StringAnalysis.WiseNLUExample;
import IncheonTour.IncheonTour.domain.Eagiggu;
import IncheonTour.IncheonTour.domain.Location;
import IncheonTour.IncheonTour.dto.FestivalDto;
import IncheonTour.IncheonTour.dto.GpsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final EagigguService eagigguService;
    private final LocationRepository locationRepository;

    /**사용자가 path를 선택*/
    @PostMapping("/path/{path_id}")
    public ResponseEntity<?> PathSelect(@PathVariable("path_id") Long pathId) { // 특정 path 선택
        // 선택한 path로 이지꾸에 path_id 저장
        eagigguService.updateEagigguPath(Integer.toUnsignedLong(1), pathId);
        return ResponseEntity.ok().build();
    }

    /**행사 정보 조회 - 현 위치가 location 중 하나이고 행사를 요청했다면, 해당 location 주변 행사 정보 전송*/
    @GetMapping("/basic")
    public ResponseEntity<?> Basic(@RequestParam(name = "str", defaultValue = "null") String str) {

        StringBuffer result = new StringBuffer();
        // str을 행사정보조회라는 것을 판별 + 인천인지 판별
        // ex) 행사시작일 -> FestivalDto를 생성하고 데이터 받아오기
        FestivalDto festivalDto = FestivalDto.createFestivalDto("2020101");
        // 받아온 데이터를 전달
        //return getFestivalInfo(festivalDto);
        return ResponseEntity.ok().body(getFestivalInfo(festivalDto));
    }

    /**카페 정보 조회 - 현 위치가 location 중 하나인지 확인하고 맞다면, 해당 location 주변 카페 정보 전송*/

    /**관광지 정보 조회 - 현 위치가 location 중 하나인지 확인하고 맞다면, 해당 location 주변 카페 정보 전송*/

    /**날씨 정보 조회 - 해당 path의 날씨 정보 전송*/

    /**실시간 위치 정보 10분 마다 전달받음, 갱신*/
    @PostMapping("/updateLocation")
    public ResponseEntity<?> updateLocation(@RequestBody GpsDto gpsDto) {
        // 현재 위치와 location의 거리를 계산해서 20m 안으로 들어왔다면 현재 location을 갱신
        String str = "NULL";

        List<Location> locations = locationRepository.findAll();
        for (Location location : locations) {
            double dist = getDistance(
                    Double.parseDouble(gpsDto.getGps_latitude()),
                    Double.parseDouble(gpsDto.getGps_longitude()),
                    Double.parseDouble(location.getGps_latitude()),
                    Double.parseDouble(location.getGps_longitude())); // 해당 location과 현위치와의 거리(meter)
            if (dist < 30) { // 30m 안으로 들어오면 eagiggu의 current_location을 갱신
                str = eagigguService.updateEagigguCurrentLocation(Integer.toUnsignedLong(1), location) + "도착";
                break;
            }
            else { // 30m 보다 멀어짐
                str = eagigguService.updateEagigguCurrentLocationNull(Integer.toUnsignedLong(1));
            }
        }
        return ResponseEntity.ok().body(str);
    }

    /*{
        "gps_latitude": "37.47645860319507",
            "gps_longitude": "126.61764599576283"
    }*/

    /*@PostMapping("/updateLocation")
    public ResponseEntity<?> updateLocation(@RequestBody GpsDto gpsDto) {

        List<Location> locations = locationRepository.findByName("해당화사진관");
        Optional<Location> location = locations.stream().filter(l -> Objects.equals(l.getName(), "해당화사진관")).findAny();

        return ResponseEntity.ok().body(getDistance(
                Double.parseDouble(gpsDto.getGps_latitude()),
                Double.parseDouble(gpsDto.getGps_longitude()),
                Double.parseDouble(location.get().getGps_latitude()),
                Double.parseDouble(location.get().getGps_longitude())));
    }*/


    /*@GetMapping("/anal")
    public String StrAnal() {
        return WiseNLUExample.Analysis();
    }*/

    public String getFestivalInfo(FestivalDto festivalDto) {
        StringBuffer sb = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService/searchFestival");
            urlBuilder.append("?MobileOS=" + festivalDto.getMobileOs());
            urlBuilder.append("&MobileApp=" + festivalDto.getMobileApp());
            urlBuilder.append("&serviceKey=" + festivalDto.getServiceKey());
            urlBuilder.append("&_type=" + festivalDto.get_type());
            urlBuilder.append("&areaCode=" + festivalDto.getAreaCode());
            urlBuilder.append("&eventStartDate=" + festivalDto.getEventStartDate());

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader br;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb + "";
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
