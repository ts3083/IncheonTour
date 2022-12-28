package IncheonTour.IncheonTour.dto;

import lombok.Data;

@Data
public class FestivalDto {

    private int numOfRows;
    private int pageNo;
    private String MobileOs;
    private String MobileApp;
    private String serviceKey;
    private String _type;
    private String listYN; // 목록구분(Y=목록, N=개수)
    private String arrange; // (A=제목순, C=수정일순, D=생성일순) 대표이미지가반드시있는정렬(O=제목순, Q=수정일순, R=생성일순)
    private String areaCode;
    private String eventStartDate; // 행사시작일(형식 :YYYYMMDD)

    // 시군구코드, 행사종료일, 콘텐츠 수정일 생략

    public static FestivalDto createFestivalDto(String startDate) {
        FestivalDto festivalDto = new FestivalDto();
        festivalDto.setMobileOs("AND");
        festivalDto.setMobileApp("abc");
        festivalDto.setServiceKey("Tt9TUg4iQI%2BRQhBkEZALgIr1XfoqUOnBQITV502D800J6paWCeHJklFrlCZG7407C1S4WtD28lTENN8x9bO3Pw%3D%3D");
        festivalDto.set_type("json");
        festivalDto.setAreaCode("2");
        festivalDto.setEventStartDate(startDate);
        return festivalDto;
    }
}
