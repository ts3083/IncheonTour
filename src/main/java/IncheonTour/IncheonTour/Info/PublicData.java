package IncheonTour.IncheonTour.Info;

import IncheonTour.IncheonTour.dto.FestivalDto;
import IncheonTour.IncheonTour.dto.RestaurantDto;
import IncheonTour.IncheonTour.dto.TouristDestinationDto;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

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

    public String getRestaurantInfo(RestaurantDto dto) {
        StringBuffer sb = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder("https://openapi.naver.com/v1/search/local.json");
            urlBuilder.append("?query=" + URLEncoder.encode(dto.getQuery(), "UTF-8"));
            urlBuilder.append("&display=" + dto.getDisplay());
            urlBuilder.append("&start=" + dto.getStart());
            urlBuilder.append("&sort=" + dto.getSort());

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Naver-Client-Id", "ArL_GZ55geUX1UzU4KvB");
            conn.setRequestProperty("X-Naver-Client-Secret", "14PnRWm6z4");
            BufferedReader br;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            conn.disconnect();
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

    /*public String getRestaurantInfo(RestaurantDto restaurantDto) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query",restaurantDto.getQuery())
                .queryParam("display",restaurantDto.getDisplay())
                .queryParam("start",restaurantDto.getStart())
                .queryParam("sort",restaurantDto.getSort())
                .encode(Charset.forName("UTF-8"))
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id","ArL_GZ55geUX1UzU4KvB")
                .header("X-Naver-Client-Secret","14PnRWm6z4")
                .build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
        return result.getBody();
    }*/
}
