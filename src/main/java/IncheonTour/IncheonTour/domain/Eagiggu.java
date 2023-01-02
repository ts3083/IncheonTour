package IncheonTour.IncheonTour.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Eagiggu {

    @Id @GeneratedValue
    @Column(name = "eagiggu_id")
    private Long id;

    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "path_id")
    private Path path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location current_location;
}
