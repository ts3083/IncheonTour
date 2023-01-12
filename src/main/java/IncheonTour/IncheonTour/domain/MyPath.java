package IncheonTour.IncheonTour.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyPath {

    @Id
    @GeneratedValue
    @Column(name = "path_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "myPath")
    private List<PathLocation> pathLocations = new ArrayList<>();

    public static MyPath createPath(String name) {
        MyPath myPath = new MyPath();
        myPath.setName(name);
        return myPath;
    }
}
