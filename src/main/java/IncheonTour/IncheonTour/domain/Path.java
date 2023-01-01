package IncheonTour.IncheonTour.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Path {

    @Id
    @GeneratedValue
    @Column(name = "path_id")
    private Long id;

    @OneToMany(mappedBy = "path")
    private List<PathLocation> pathLocations = new ArrayList<>();
}
