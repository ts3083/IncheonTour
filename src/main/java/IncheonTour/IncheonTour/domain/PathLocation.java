package IncheonTour.IncheonTour.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PathLocation {

    @Id
    @GeneratedValue
    @Column(name = "path_location_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "path_id")
    private MyPath myPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    // 연관관계 메서드
    public void setMyPath(MyPath myPath) {
        this.myPath = myPath;
        myPath.getPathLocations().add(this);
    }

    // 생성 메서드
    public static PathLocation createPathLocation(MyPath myPath, Location location) {
        PathLocation pathLocation = new PathLocation();
        pathLocation.setMyPath(myPath);
        pathLocation.setLocation(location);
        return pathLocation;
    }
}
