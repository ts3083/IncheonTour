package IncheonTour.IncheonTour.dto;

import IncheonTour.IncheonTour.domain.Location;
import lombok.Data;

@Data
public class TouristDestinationDto {

    private int numOfRows;
    private int pageNo;
    private String MobileOs;
    private String MobileApp;
    private String serviceKey;
    private String _type;
    private String listYN; // 목록구분(Y=목록, N=개수)
    private String arrange; // (A=제목순, C=수정일순, D=생성일순) 대표이미지가반드시있는정렬(O=제목순, Q=수정일순, R=생성일순)
    private String gps_latitude; // 위도
    private String gps_longitude; // 경도
    private String radius; // 거리반경(단위:m)

    public static TouristDestinationDto createTouristDestinationDto(Location location) {
        TouristDestinationDto touristDestinationDto = new TouristDestinationDto();
        touristDestinationDto.setMobileOs("AND");
        touristDestinationDto.setMobileApp("abc");
        touristDestinationDto.setServiceKey("Tt9TUg4iQI%2BRQhBkEZALgIr1XfoqUOnBQITV502D800J6paWCeHJklFrlCZG7407C1S4WtD28lTENN8x9bO3Pw%3D%3D");
        touristDestinationDto.set_type("json");
        touristDestinationDto.setGps_latitude(location.getGps_latitude());
        touristDestinationDto.setGps_longitude(location.getGps_longitude());
        touristDestinationDto.setRadius("150"); // 거리반경: 150m
        return touristDestinationDto;
    }
}
