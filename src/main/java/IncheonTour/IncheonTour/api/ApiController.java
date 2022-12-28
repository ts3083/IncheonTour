package IncheonTour.IncheonTour.api;

import IncheonTour.IncheonTour.StringAnalysis.WiseNLUExample;
import IncheonTour.IncheonTour.dto.FestivalDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/basic")
    public String Basic(@RequestParam(name = "str", defaultValue = "null") String str) {

        StringBuffer result = new StringBuffer();
        // str을 행사정보조회라는 것을 판별 + 인천인지 판별
        // ex) 행사시작일 -> FestivalDto를 생성하고 데이터 받아오기
        FestivalDto festivalDto = FestivalDto.createFestivalDto("2020101");
        // 받아온 데이터를 전달
        return getFestivalInfo(festivalDto);
    }

    @GetMapping("/anal")
    public String StrAnal() {
        return WiseNLUExample.Analysis();
    }

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
}
