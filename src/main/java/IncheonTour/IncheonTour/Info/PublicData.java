package IncheonTour.IncheonTour.Info;

import IncheonTour.IncheonTour.Repsotory.LocationRepository;
import IncheonTour.IncheonTour.domain.Location;
import IncheonTour.IncheonTour.dto.FestivalDto;
import IncheonTour.IncheonTour.dto.RestaurantDto;
import IncheonTour.IncheonTour.dto.TouristDestinationDto;
import antlr.StringUtils;
import com.google.gson.JsonObject;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.internal.Pair;
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
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PublicData {
    private final LocationRepository locationRepository;

    public PublicData(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
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

    public String getWeatherInfo(List<Location> locations) {
        // 각 location의 구까지 비교하여 중복되지 않는 지역 정보를 가져옴
        // 반복문을 돌면서 해당 지역 정보의 x, y 격자값을 미리 저장한 값에서 읽어오고 날씨 조회하여 string에 append
        // string 반환
        Map<String, Pair<String, String>> mapInfo = new HashMap<>();
        locations.forEach(location
                -> mapInfo.put(location.getSigungu().getName(),
                Pair.of(location.getSigungu().getNx(), location.getSigungu().getNy())));

        StringBuffer sb = new StringBuffer();
        for(String key : mapInfo.keySet()) {
            sb.append("인천 " + key + " 의 현재 날씨입니다" + "\n");
            sb.append(getWeatherInfoDetail(mapInfo.get(key).getLeft(), mapInfo.get(key).getRight(), getBaseTime(), getBaseDate()));
        }

        return sb + "";
    }

    public String getWeatherInfoDetail(String nx, String ny, String baseTime, String baseDate) {
        StringBuffer sb = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst");
            urlBuilder.append("?serviceKey=Tt9TUg4iQI%2BRQhBkEZALgIr1XfoqUOnBQITV502D800J6paWCeHJklFrlCZG7407C1S4WtD28lTENN8x9bO3Pw%3D%3D");
            urlBuilder.append("&pageNo=1");
            urlBuilder.append("&numOfRows=1000");
            urlBuilder.append("&dataType=JSON");
            urlBuilder.append("&base_date=" + baseDate);
            urlBuilder.append("&base_time=" + baseTime);
            urlBuilder.append("&nx=" + nx);
            urlBuilder.append("&ny=" + ny);

            requestInfo(sb, urlBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb + "";

        /*String result = sb.toString();

        // response 키를 가지고 데이터를 파싱
        JSONObject jsonObj_1 = new JSONObject(result);
        String response = jsonObj_1.getString("response");

        // response 로 부터 body 찾기
        JSONObject jsonObj_2 = new JSONObject(response);
        String body = jsonObj_2.getString("body");

        // body 로 부터 items 찾기
        JSONObject jsonObj_3 = new JSONObject(body);
        String items = jsonObj_3.getString("items");

        // items로 부터 itemlist 를 받기
        JSONObject jsonObj_4 = new JSONObject(items);
        JSONArray jsonArray = jsonObj_4.getJSONArray("item");

        StringBuffer weather = new StringBuffer();

        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObj_4 = jsonArray.getJSONObject(i);
            String category = jsonObj_4.getString("category");
            String obsrValue = jsonObj_4.getString("obsrValue");

            if (category.equals("T1H")) {
                weather.append("기온은 " + obsrValue + "℃ 입니다.\n");
            }
            if (category.equals("REH")) {
                weather.append("습도는 " + obsrValue + "% 입니다.\n");
            }
            if (category.equals("PTY")) {
                switch (obsrValue) {
                    case "0":
                        weather.append("강수는 없습니다.\n");
                        break;
                    case "1":
                        weather.append("현재 비가 오고 있습니다.\n");
                        break;
                    case "2":
                        weather.append("현재 비와 눈이 오고 있습니다.\n");
                        break;
                    case "3":
                        weather.append("현재 눈이 오고 있습니다.\n");
                        break;
                    case "5":
                        weather.append("현재 빗방울이 떨어지고 있습니다.\n");
                        break;
                    case "6":
                        weather.append("현재 빗방울과 눈날림이 있습니다.\n");
                        break;
                    case "7":
                        weather.append("현재 눈날림이 있습니다.\n");
                        break;
                    default:
                        weather.append("강수 정보는 없습니다.\n");
                }
            }
        }
        return weather + "";*/
    }

    private String getBaseTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH00"));
    }

    private String getBaseDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        return strToday;
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
