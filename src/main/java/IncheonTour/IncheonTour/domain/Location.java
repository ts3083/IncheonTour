package IncheonTour.IncheonTour.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue
    @Column(name = "location_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sigungu_id")
    private Sigungu sigungu;

    private String gps_latitude; // 위도

    private String gps_longitude; // 경도

    public static Location createLocation(String name, Sigungu sigungu, String lat, String lon) {
        Location location = new Location();
        location.setName(name);
        location.setSigungu(sigungu);
        location.setGps_latitude(lat);
        location.setGps_longitude(lon);
        return location;
    }
}
