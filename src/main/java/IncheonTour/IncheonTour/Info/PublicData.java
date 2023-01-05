package IncheonTour.IncheonTour.Info;

import IncheonTour.IncheonTour.dto.FestivalDto;
import IncheonTour.IncheonTour.dto.TouristDestinationDto;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class PublicData {

    public String getFestivalInfo(FestivalDto festivalDto) {
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

            requestInfo(sb, urlBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb + "";
    }

    public String getTouristDestinationInfo(TouristDestinationDto dto) {
        StringBuffer sb = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService/locationBasedList");
            urlBuilder.append("?MobileOS=" + dto.getMobileOs());
            urlBuilder.append("&MobileApp=" + dto.getMobileApp());
            urlBuilder.append("&serviceKey=" + dto.getServiceKey());
            urlBuilder.append("&_type=" + dto.get_type());
            urlBuilder.append("&mapX=" + dto.getGps_longitude());
            urlBuilder.append("&mapY=" + dto.getGps_latitude());
            urlBuilder.append("&radius=" + dto.getRadius());

            requestInfo(sb, urlBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb + "";
    }

    private void requestInfo(StringBuffer sb, StringBuilder urlBuilder) throws IOException {
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
    }
}
