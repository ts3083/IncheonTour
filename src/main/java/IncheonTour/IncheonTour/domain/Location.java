package IncheonTour.IncheonTour.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    @Id
    @GeneratedValue
    @Column(name = "location_id")
    private Long id;

    private String name;

    private String gps_x;

    private String gps_y;
}
