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

    private String image_path;
    private String image_name;

    private String gps_latitude; // 위도

    private String gps_longitude; // 경도

    public static Location createLocation(String name, String imagePath, String imageName,
                                          Sigungu sigungu, String lat, String lon) {
        Location location = new Location();
        location.setName(name);
        location.setImage_path(imagePath);
        location.setImage_name(imageName);
        location.setSigungu(sigungu);
        location.setGps_latitude(lat);
        location.setGps_longitude(lon);
        return location;
    }
}
