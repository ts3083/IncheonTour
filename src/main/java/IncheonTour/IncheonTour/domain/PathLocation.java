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
    private Path path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    // 연관관계 메서드
    public void setPath(Path path) {
        this.path = path;
        path.getPathLocations().add(this);
    }

    // 생성 메서드
    public static PathLocation createPathLocation(Path path, Location location) {
        PathLocation pathLocation = new PathLocation();
        pathLocation.setPath(path);
        pathLocation.setLocation(location);
        return pathLocation;
    }
}
