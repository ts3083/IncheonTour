package IncheonTour.IncheonTour.api;

import IncheonTour.IncheonTour.Info.PublicData;
import IncheonTour.IncheonTour.Repsotory.LocationRepository;
import IncheonTour.IncheonTour.Service.EagigguService;
import IncheonTour.IncheonTour.Service.LocationService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final EagigguService eagigguService;
    private final LocationService locationService;
    private final PublicData publicData;

    /**사용자가 path를 선택*/
    @PostMapping("/path/{path_id}")
    public ResponseEntity<?> PathSelect(@PathVariable("path_id") Long pathId) { // 특정 path 선택
        // 선택한 path로 이지꾸에 path_id 저장
        eagigguService.updateEagigguPath(Integer.toUnsignedLong(1), pathId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/basic")
    public ResponseEntity<?> Basic(@RequestParam(name = "str", defaultValue = "null") String str) {
        // eagiggu의 location 확인
        Location location = eagigguService.getEagigguById(Integer.toUnsignedLong(1)).getCurrent_location();
        if(location == null) {
            return ResponseEntity.ok().body("아직 장소에 도착하지 않았습니다");
        }

        StringBuffer result = new StringBuffer();
        /**행사 정보 조회 - 현 위치가 location 중 하나이고 행사를 요청했다면, 해당 location 주변 행사 정보 전송*/
        if (str.contains("행사")) { // str을 행사정보조회라는 것을 판별
            FestivalDto festivalDtoPathNum1 = FestivalDto.createFestivalDtoPathNum1();
            return ResponseEntity.ok().body(publicData.getFestivalInfo(festivalDtoPathNum1));
        }
        /**관광지 정보 조회 - 현 위치가 location 중 하나인지 확인하고 맞다면, 해당 location 주변 카페 정보 전송*/
        else if (str.contains("관광지")) {

        }
        /**카페 정보 조회 - 현 위치가 location 중 하나인지 확인하고 맞다면, 해당 location 주변 카페 정보 전송*/
        else if (str.contains("관광지")) {

        }
        /**날씨 정보 조회 - 해당 path의 날씨 정보 전송*/
        else if (str.contains("날씨")) {

        }
        // 받아온 데이터를 전달
        //return getFestivalInfo(festivalDto);
        return ResponseEntity.ok().body("다시 말해주세요");
    }

    /**실시간 위치 정보 10분 마다 전달받음, 갱신*/
    @PostMapping("/updateLocation")
    public ResponseEntity<?> updateLocation(@RequestBody GpsDto gpsDto) {
        // 현재 위치와 location의 거리를 계산해서 20m 안으로 들어왔다면 현재 location을 갱신
        String str = locationService.updateCurrentLocation(gpsDto);
        return ResponseEntity.ok().body(str);
    }

    /*{
        "gps_latitude": "37.47736867579668",
        "gps_longitude": "126.61766397005596"
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

    /*public String getFestivalInfo(FestivalDto festivalDto) {
        StringBuffer sb = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService/searchFestival");
            urlBuilder.append("?MobileOS=" + festivalDto.getMobileOs());
            urlBuilder.append("&MobileApp=" + festivalDto.getMobileApp());
            urlBuilder.append("&serviceKey=" + festivalDto.getServiceKey());
            urlBuilder.append("&_type=" + festivalDto.get_type());
            urlBuilder.append("&areaCode=" + festivalDto.getAreaCode());
            urlBuilder.append("&sigunguCode=" + festivalDto.getSigunguCode());
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
    }*/


}
